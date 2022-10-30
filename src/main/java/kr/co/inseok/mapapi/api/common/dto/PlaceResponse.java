package kr.co.inseok.mapapi.api.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class PlaceResponse {
    
    private String title;

    @Builder
    public PlaceResponse(String title) {
        this.title = title;
    }
}
