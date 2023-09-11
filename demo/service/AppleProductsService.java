package com.example.demo.service;

import com.example.demo.dto.AppleProductsDto;

import java.util.List;

public interface AppleProductsService {
    AppleProductsDto createStock(AppleProductsDto appleProductsDto);

    AppleProductsDto findStock(Long productId);

    AppleProductsDto updateStock(Long productId, AppleProductsDto dto);

    List<AppleProductsDto> findAllStock();

    AppleProductsDto addProductsInStock(Long id, Integer amount);

    List<AppleProductsDto> findAllActiveStock();

    List<AppleProductsDto> findAllInactiveStock();

}
