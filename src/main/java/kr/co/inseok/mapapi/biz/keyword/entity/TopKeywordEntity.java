package kr.co.inseok.mapapi.biz.keyword.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity(name = "topKeyword")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TopKeywordEntity {

    @Id
    private String keyword;

    private int searchCount;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Builder
    public TopKeywordEntity(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public void searchCountPlusOne() {
        searchCount = searchCount + NumberUtils.INTEGER_ONE;
    }
}