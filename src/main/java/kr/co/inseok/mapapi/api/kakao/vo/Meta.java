package kr.co.inseok.mapapi.api.kakao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Meta {
    @JsonProperty("is_end")
    private boolean isEnd;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("pageable_count")
    private int pageableCount;

    @JsonProperty("same_name")
    private SameName sameName;

    @Builder
    public Meta(boolean isEnd, int totalCount, int pageableCount, SameName sameName) {
        this.isEnd = isEnd;
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.sameName = sameName;
    }
}
