package com.example.demo.controller;

import com.example.demo.dto.AppleProductsDiscountsDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.AppleProductsDiscountsService;
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
@RequestMapping("/apple-discounts")
@Tag(name = "Apple Products' Discount Controller", description = "Methods for Apple products' discounts")
@Validated
@Slf4j
public class AppleDiscountController {

    private final AppleProductsDiscountsService service;

    @PostMapping("/create-discount")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<AppleProductsDiscountsDto> createDiscount(
            @Valid @RequestBody AppleProductsDiscountsDto dto
    ) {
        log.info("method for creating discount is called,{}",dto);
        return ResponseCreator.createSuccessResponse(service.createDiscount(dto));
    }

    @GetMapping("/find-discount")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<AppleProductsDiscountsDto> findDiscount(
            @Valid @RequestParam Long discountId
    ) {
        log.info("method for search a discount is called,{}",discountId);
        return ResponseCreator.createSuccessResponse(service.findDiscount(discountId));
    }

    @PutMapping("/modify-discount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto<AppleProductsDiscountsDto> updateDiscount(
            @Valid @RequestParam Long discountId,
            @Valid @RequestBody AppleProductsDiscountsDto dto
    ) {
        log.info("method to modify discount is called {},{}",discountId,dto);
        return ResponseCreator.createSuccessResponse(service.updateDiscount(discountId, dto));
    }

    @GetMapping("/find-all-discounts")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDiscountsDto>> findAllDiscounts() {
        log.info("method to find all discounts is called");
        return ResponseCreator.createSuccessResponse(service.findAllDiscounts());
    }

    @GetMapping("/find-active-discounts")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDiscountsDto>> findActiveDiscounts() {
        log.info("method to find all active discounts is called");
        return ResponseCreator.createSuccessResponse(service.findActiveDiscounts());
    }

    @GetMapping("/find-inactive-discounts")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseDto<List<AppleProductsDiscountsDto>> findInactiveDiscounts() {
        log.info("method to find all active discounts is called");
        return ResponseCreator.createSuccessResponse(service.findInActiveDiscounts());
    }
}
