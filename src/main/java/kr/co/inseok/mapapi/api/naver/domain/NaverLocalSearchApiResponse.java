package kr.co.inseok.mapapi.api.naver.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class NaverLocalSearchApiResponse {
    @JsonProperty("items")
    private List<Items> items;

    @JsonProperty("display")
    private int display;

    @JsonProperty("start")
    private int start;

    @JsonProperty("total")
    private int total;

    @JsonProperty("lastBuildDate")
    private String lastbuilddate;

    @Builder
    public NaverLocalSearchApiResponse(List<Items> items, int display, int start, int total, String lastbuilddate) {
        this.items = items;
        this.display = display;
        this.start = start;
        this.total = total;
        this.lastbuilddate = lastbuilddate;
    }
}
