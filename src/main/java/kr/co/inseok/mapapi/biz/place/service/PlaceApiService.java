package kr.co.inseok.mapapi.biz.place.service;

import kr.co.inseok.mapapi.api.common.dto.PlaceResponse;
import kr.co.inseok.mapapi.api.common.service.MapApiService;
import kr.co.inseok.mapapi.api.common.vo.Place;
import kr.co.inseok.mapapi.framework.error.ErrorMessageEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlaceApiService {
    private static final int API_PLACE_COUNT = 5;
    private static final int TOAL_PLACE_COUNT = 10;

    @Qualifier("kakaoApiService")
    private final MapApiService kakaoApiService;

    @Qualifier("naverApiService")
    private final MapApiService naverApiService;

    @Cacheable(value = "searchPlaceCache", key = "#query")
    public List<PlaceResponse> searchPlace(String query) {
        if (StringUtils.isBlank(query)) {
            throw new IllegalArgumentException(ErrorMessageEnum.QUERY_REQUIRED.getErrorMessage());
        }

        Queue<Place> sparePlaceList = new LinkedList<>();

        List<Place> kakaoPlaceList = kakaoApiService.localSearch(query);
        kakaoPlaceList = kakaoPlaceList.stream().distinct().collect(Collectors.toList()); // 리스트 자체 중복 요소 제거
        if (kakaoPlaceList.size() > API_PLACE_COUNT) {
            sparePlaceList.addAll(kakaoPlaceList.subList(API_PLACE_COUNT, kakaoPlaceList.size()));
            kakaoPlaceList = kakaoPlaceList.subList(NumberUtils.INTEGER_ZERO, API_PLACE_COUNT);
        }

        List<Place> naverPlaceList = naverApiService.localSearch(query);
        naverPlaceList = naverPlaceList.stream().distinct().collect(Collectors.toList()); // 리스트 자체 중복 요소 제거
        if (naverPlaceList.size() > API_PLACE_COUNT) {
            sparePlaceList.addAll(naverPlaceList.subList(API_PLACE_COUNT, naverPlaceList.size()));
            naverPlaceList = naverPlaceList.subList(NumberUtils.INTEGER_ZERO, API_PLACE_COUNT);
        }

        // 검색 결과 합이 10개 미만이면 여분의 검색결과를 이용하여 최대한 10개를 맞춰줌
        if (kakaoPlaceList.size() + naverPlaceList.size() < TOAL_PLACE_COUNT) {
            while (!sparePlaceList.isEmpty() && kakaoPlaceList.size() + naverPlaceList.size() != TOAL_PLACE_COUNT) {
                if (kakaoPlaceList.size() < API_PLACE_COUNT) {
                    kakaoPlaceList.add(sparePlaceList.poll());
                }

                if (naverPlaceList.size() < API_PLACE_COUNT) {
                    naverPlaceList.add(sparePlaceList.poll());
                }
            }
        }

        List<Place> dupPlaceList = new ArrayList<>(kakaoPlaceList);

        dupPlaceList.retainAll(naverPlaceList); // 중복요소만 추출
        kakaoPlaceList.removeAll(dupPlaceList); // 카카오 API 리스트 중복 요소 제거
        naverPlaceList.removeAll(dupPlaceList); // 네이버 API 리스트 중복 요소 제거

        return Stream.of(dupPlaceList, kakaoPlaceList, naverPlaceList)
                .flatMap(Collection::stream)
                .map(place -> PlaceResponse.builder()
                        .title(place.getTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
