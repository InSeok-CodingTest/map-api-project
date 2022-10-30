package kr.co.inseok.mapapi.api.naver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Items {
    @JsonProperty("mapy")
    private String mapy;

    @JsonProperty("mapx")
    private String mapx;

    @JsonProperty("roadAddress")
    private String roadaddress;

    @JsonProperty("address")
    private String address;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("link")
    private String link;

    @JsonProperty("title")
    private String title;

    @Builder
    public Items(String mapy, String mapx, String roadaddress, String address, String telephone, String description, String category, String link, String title) {
        this.mapy = mapy;
        this.mapx = mapx;
        this.roadaddress = roadaddress;
        this.address = address;
        this.telephone = telephone;
        this.description = description;
        this.category = category;
        this.link = link;
        this.title = title;
    }
}
