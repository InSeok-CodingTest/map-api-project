package kr.co.inseok.mapapi.api.kakao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperty {
    private String host;
    private String restApiKey;
}
