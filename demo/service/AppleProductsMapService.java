package com.example.demo.service;


import com.example.demo.dto.AppleProductsMapDto;

import java.util.List;

public interface AppleProductsMapService {
    AppleProductsMapDto createMap(AppleProductsMapDto dto);

    AppleProductsMapDto findMap(Long mapId);

    AppleProductsMapDto updateMap(Long mapId, AppleProductsMapDto dto);

    List<AppleProductsMapDto> findAllMap();
}
