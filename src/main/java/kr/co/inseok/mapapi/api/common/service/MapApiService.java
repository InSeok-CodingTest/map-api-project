package kr.co.inseok.mapapi.api.common.service;

import kr.co.inseok.mapapi.api.common.vo.Place;

import java.util.List;

public interface MapApiService {

    List<Place> localSearch(String query);
}
