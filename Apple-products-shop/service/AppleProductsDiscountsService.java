package com.example.demo.service;

import com.example.demo.dto.AppleProductsDiscountsDto;

import java.util.List;

public interface AppleProductsDiscountsService {
    AppleProductsDiscountsDto createDiscount(AppleProductsDiscountsDto dto);

    AppleProductsDiscountsDto findDiscount(Long discountId);

    AppleProductsDiscountsDto updateDiscount(Long discountId, AppleProductsDiscountsDto dto);

    List<AppleProductsDiscountsDto> findAllDiscounts();

    List<AppleProductsDiscountsDto> findActiveDiscounts();

    List<AppleProductsDiscountsDto> findInActiveDiscounts();

    Boolean checkIfThereIsDiscount(Long productId);

    Integer discountCalculus(Integer percentage, Integer price);

    Integer getDiscountPercentage(Long productId);

}
