package kr.co.inseok.mapapi.biz.keyword.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class TopKeywordResponse {

    private String keyword;

    private int searchCount;

    @Builder
    public TopKeywordResponse(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }
}