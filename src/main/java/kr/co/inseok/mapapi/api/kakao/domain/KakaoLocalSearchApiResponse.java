package kr.co.inseok.mapapi.api.kakao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class KakaoLocalSearchApiResponse {
    @JsonProperty("documents")
    private List<Documents> documents;

    @JsonProperty("meta")
    private Meta meta;

    @Builder
    public KakaoLocalSearchApiResponse(List<Documents> documents, Meta meta) {
        this.documents = documents;
        this.meta = meta;
    }
}
