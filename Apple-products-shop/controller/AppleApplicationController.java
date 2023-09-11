package com.example.demo.controller;


import com.example.demo.dto.AppleProductsDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsTypesEnum;
import com.example.demo.service.AppleAppService;
import com.example.demo.util.ResponseCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apple-application")
@Tag(name = "Apple Application Controller", description = "Controller with mutual work of services")
@RequiredArgsConstructor
@Slf4j
@Validated
public class AppleApplicationController {
    private final AppleAppService service;


    @GetMapping("/is-product-active")
    public ResponseDto<Boolean> isProductActive(
           @Valid @RequestParam ProductsTypesEnum typesEnum,
           @Valid @RequestParam ProductsModelsEnum modelsEnum
    ) {
        log.info("method analyzing if product is active is called, {}.{}",typesEnum,modelsEnum);
        return ResponseCreator.createSuccessResponse(service.isProductActive(typesEnum, modelsEnum));
    }
    @GetMapping("/info-stock-actual-price/")
    public ResponseDto<AppleProductsDto> findStockWithActualPrice(
            @Valid @RequestParam Long productId
    ){
        log.info("method for search stock with actual price is called,{}",productId);
        return ResponseCreator.createSuccessResponse(service.findStockWithActualPrice(productId));
    }

}
