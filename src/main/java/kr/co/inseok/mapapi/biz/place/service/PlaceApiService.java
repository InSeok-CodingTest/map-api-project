package kr.co.inseok.mapapi.biz.place.service;

import kr.co.inseok.mapapi.api.common.dto.PlaceResponse;
import kr.co.inseok.mapapi.api.common.service.MapApiService;
import kr.co.inseok.mapapi.api.common.vo.Place;
import kr.co.inseok.mapapi.framework.error.ErrorMessageEnum;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlaceApiService {

    @Qualifier("kakaoApiService")
    private final MapApiService kakaoApiService;

    @Qualifier("naverApiService")
    private final MapApiService naverApiService;

    @Cacheable(value = "searchPlaceCache", key = "#query")
    public List<PlaceResponse> searchPlace(String query) {
        if (StringUtils.isBlank(query)) {
            throw new RuntimeException(ErrorMessageEnum.QUERY_REQUIRED.getErrorMessage());
        }

        List<Place> kakaoPlaceList = kakaoApiService.localSearch(query);
        List<Place> naverPlaceList = naverApiService.localSearch(query);
        List<Place> dupPlaceList = new ArrayList<>(kakaoPlaceList);

        dupPlaceList.retainAll(naverPlaceList); // 중복요소만 추출
        kakaoPlaceList.removeAll(dupPlaceList); // 카카오 API 리스트 중복 요소 제거
        naverPlaceList.removeAll(dupPlaceList); // 네이버 API 리스트 중복 요소 제거

        return Stream.of(kakaoPlaceList, naverPlaceList)
                .flatMap(Collection::stream)
                .map(place -> PlaceResponse.builder()
                        .title(place.getTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
