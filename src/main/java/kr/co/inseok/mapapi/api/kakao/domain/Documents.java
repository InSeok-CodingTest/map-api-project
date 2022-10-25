package kr.co.inseok.mapapi.api.kakao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Documents {
    @JsonProperty("y")
    private String y;

    @JsonProperty("x")
    private String x;

    @JsonProperty("category_group_name")
    private String categoryGroupName;

    @JsonProperty("category_group_code")
    private String categoryGroupCode;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("id")
    private String id;

    @JsonProperty("road_address_name")
    private String roadAddressName;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("place_url")
    private String placeUrl;

    @JsonProperty("distance")
    private String distance;

    @JsonProperty("place_name")
    private String placeName;

    @Builder
    public Documents(String y, String x, String categoryGroupName, String categoryGroupCode, String phone, String id, String roadAddressName, String addressName, String categoryName, String placeUrl, String distance, String placeName) {
        this.y = y;
        this.x = x;
        this.categoryGroupName = categoryGroupName;
        this.categoryGroupCode = categoryGroupCode;
        this.phone = phone;
        this.id = id;
        this.roadAddressName = roadAddressName;
        this.addressName = addressName;
        this.categoryName = categoryName;
        this.placeUrl = placeUrl;
        this.distance = distance;
        this.placeName = placeName;
    }
}
