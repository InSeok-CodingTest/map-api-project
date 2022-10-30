package kr.co.inseok.mapapi.api.naver.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import kr.co.inseok.mapapi.api.common.service.MapApiService;
import kr.co.inseok.mapapi.api.common.vo.Place;
import kr.co.inseok.mapapi.api.naver.config.NaverProperty;
import kr.co.inseok.mapapi.api.naver.vo.NaverLocalSearchApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverApiService implements MapApiService {
    private static final int PAGE_SIZE = 10;

    private static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    private final NaverProperty naverProperty;

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "circuitbreaker-map-api", fallbackMethod = "localSearchFallback")
    public List<Place> localSearch(String query) {
        String url = naverProperty.getHost() + "/v1/search/local.json";

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url)
                .queryParam("query", query)
                .queryParam("display", PAGE_SIZE)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(X_NAVER_CLIENT_ID, naverProperty.getClientId());
        httpHeaders.set(X_NAVER_CLIENT_SECRET, naverProperty.getClientSecret());

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<NaverLocalSearchApiResponse> responseEntity = restTemplate
                .exchange(uriComponents.toUri().toString(), HttpMethod.GET, httpEntity, NaverLocalSearchApiResponse.class);

        return Objects.requireNonNull(responseEntity.getBody())
                .getItems()
                .stream()
                .map(items -> Place.builder()
                        .title(removeHtmlTag(items.getTitle()))
                        .comparisonTitle(removeHtmlTag(items.getTitle()).toUpperCase().trim())
                        .build())
                .collect(Collectors.toList());
    }

    // 에러발생 시 해당 Fallback 동작
    private List<Place> localSearchFallback(Throwable e) {
        log.warn("네이버 API 이상 발생 : {}", ExceptionUtils.getStackTrace(e));
        return new ArrayList<>();
    }

    // 에러 실패율 달성하여 해당 Fallback 동작
    private List<Place> localSearchFallback(CallNotPermittedException e) {
        log.warn("네이버 API 이상 발생으로 인한 서킷브레이커 작동 : {}", ExceptionUtils.getStackTrace(e));
        return new ArrayList<>();
    }

    private String removeHtmlTag(String title) {
        return title.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", StringUtils.EMPTY);
    }
}
