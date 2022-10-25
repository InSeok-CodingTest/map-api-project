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
public class SameName {
    @JsonProperty("selected_region")
    private String selectedRegion;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("region")
    private List<String> region;

    @Builder
    public SameName(String selectedRegion, String keyword, List<String> region) {
        this.selectedRegion = selectedRegion;
        this.keyword = keyword;
        this.region = region;
    }
}
