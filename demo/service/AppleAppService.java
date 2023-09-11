package com.example.demo.service;


import com.example.demo.dto.AppleProductsDto;
import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsTypesEnum;


public interface AppleAppService {

    Boolean isProductActive(ProductsTypesEnum typesEnum, ProductsModelsEnum modelsEnum);

    AppleProductsDto findStockWithActualPrice(Long productId);
}
