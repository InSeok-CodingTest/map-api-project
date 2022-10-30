package kr.co.inseok.mapapi.biz.place.controller;

import kr.co.inseok.mapapi.api.common.dto.PlaceResponse;
import kr.co.inseok.mapapi.biz.place.service.PlaceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mapApi")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping(value = "/place")
    public List<PlaceResponse> searchPlace(@RequestParam @NonNull String query) {
        return placeService.searchPlace(query);
    }
}
