package kr.co.inseok.mapapi.api.kakao.service;

import kr.co.inseok.mapapi.api.kakao.config.KakaoProperty;
import kr.co.inseok.mapapi.api.kakao.domain.KakaoLocalSearchApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final KakaoProperty kakaoProperty;

    public KakaoLocalSearchApiResponse localSearch() {

    }
}
