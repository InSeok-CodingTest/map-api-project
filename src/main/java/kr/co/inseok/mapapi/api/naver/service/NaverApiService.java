package kr.co.inseok.mapapi.api.naver.service;

import kr.co.inseok.mapapi.api.naver.config.NaverProperty;
import kr.co.inseok.mapapi.api.naver.domain.NaverLocalSearchApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverApiService {

    private final NaverProperty naverProperty;

    public NaverLocalSearchApiResponse localSearch() {
        return null;
    }
}
