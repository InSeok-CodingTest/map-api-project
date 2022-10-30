package kr.co.inseok.mapapi.biz.keyword.service;

import kr.co.inseok.mapapi.biz.keyword.dto.TopKeywordResponse;
import kr.co.inseok.mapapi.biz.keyword.entity.TopKeywordEntity;
import kr.co.inseok.mapapi.biz.keyword.repository.TopKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopKeywordService {
    private static final int PAGE_MAX_SIZE = 10;

    private final TopKeywordRepository topKeywordRepository;

    public List<TopKeywordResponse> topKeywordList() {
        return topKeywordRepository.findAll(Pageable.ofSize(PAGE_MAX_SIZE))
                .stream()
                .map(topKeywordEntity -> TopKeywordResponse.builder()
                        .keyword(topKeywordEntity.getKeyword())
                        .searchCount(topKeywordEntity.getSearchCount())
                        .build())
                .collect(Collectors.toList());
    }

    public void updateTopKeyword(String title) {
        TopKeywordEntity topKeywordEntity = topKeywordRepository.findById(title).orElse(null);
        if (Objects.nonNull(topKeywordEntity)) {
            topKeywordEntity.searchCountPlusOne();
            topKeywordRepository.save(topKeywordEntity);
        } else {
            topKeywordRepository.save(TopKeywordEntity.builder()
                    .keyword(title)
                    .searchCount(NumberUtils.INTEGER_ONE)
                    .build());
        }
    }
}
