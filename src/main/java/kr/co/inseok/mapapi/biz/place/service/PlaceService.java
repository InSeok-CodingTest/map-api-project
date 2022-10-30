package kr.co.inseok.mapapi.biz.place.service;

import kr.co.inseok.mapapi.api.common.dto.PlaceResponse;
import kr.co.inseok.mapapi.biz.keyword.service.TopKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceApiService placeApiService;

    private final TopKeywordService topKeywordService;

    public List<PlaceResponse> searchPlace(String query) {
        List<PlaceResponse> resultList = placeApiService.searchPlace(query);

        topKeywordService.updateTopKeyword(query);

        return resultList;
    }

}
