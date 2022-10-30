package kr.co.inseok.mapapi.api.common.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Place {
    private String title;
    private String comparisonTitle;

    @Builder
    public Place(String title, String comparisonTitle) {
        this.title = title;
        this.comparisonTitle = comparisonTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Place) {
            Place place = (Place) obj;
            return this.comparisonTitle.equals(place.getComparisonTitle());
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return comparisonTitle.hashCode();
    }
}
