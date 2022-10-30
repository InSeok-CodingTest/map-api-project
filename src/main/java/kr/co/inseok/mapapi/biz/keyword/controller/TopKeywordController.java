package kr.co.inseok.mapapi.biz.keyword.controller;

import kr.co.inseok.mapapi.biz.keyword.dto.TopKeywordResponse;
import kr.co.inseok.mapapi.biz.keyword.service.TopKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mapApi")
public class TopKeywordController {

    private final TopKeywordService topKeywordService;

    @GetMapping(value = "/keyword/top")
    public List<TopKeywordResponse> topKeywordList() {
        return topKeywordService.topKeywordList();
    }
}
