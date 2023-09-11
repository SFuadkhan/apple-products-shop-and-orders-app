package com.example.demo.controller;


import com.example.demo.dto.AppleProductsMapDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.AppleProductsMapService;
import com.example.demo.util.ResponseCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apple-products-map")
@Validated
@Slf4j
@Tag(name = "Apple Products' Map Controller", description = "Methods for Apple products' Map")
public class AppleProductsMapController {
    private final AppleProductsMapService service;

    @PostMapping("/create-map")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<AppleProductsMapDto> createProductsMap(
            @Valid @RequestBody AppleProductsMapDto dto
    ) {
        log.info("method for creating map is called,{}",dto);
        return ResponseCreator.createSuccessResponse(service.createMap(dto));
    }

    @GetMapping("/find-map")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<AppleProductsMapDto> findMap(
            @Valid @RequestParam Long mapId
    ) {
        log.info("method for search a map is called,{}",mapId);
        return ResponseCreator.createSuccessResponse(service.findMap(mapId));
    }

    @PutMapping("/modify-map")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto<AppleProductsMapDto> updateStock(
            @Valid @RequestParam Long mapId,
            @Valid @RequestBody AppleProductsMapDto dto
    ) {
        log.info("method to modify the map is called {},{}",mapId,dto);
        return ResponseCreator.createSuccessResponse(service.updateMap(mapId, dto));
    }

    @GetMapping("/find-all-maps")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsMapDto>> findAllMaps() {
        log.info("method to find all maps is called");
        return ResponseCreator.createSuccessResponse(service.findAllMap());
    }
}
