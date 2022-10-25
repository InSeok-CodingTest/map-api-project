package kr.co.inseok.mapapi.api.naver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "naver")
public class NaverProperty {
    private String host;
    private String clientId;
    private String clientSecret;
}
