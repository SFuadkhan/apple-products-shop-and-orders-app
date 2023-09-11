package com.example.demo.dto;

import com.example.demo.enums.ProductsStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppleProductsDiscountsDto {
    @Min(value = 1,message = "id must be at least ,{value}")
    private Long id;
    @NotNull
    @Min(value = 1,message = "percentage must be at least ,{value}")
    private Integer discountPercentage;

    @NotNull(message = "startDate can't be null")
    private LocalDate startDate;

    @NotNull(message = "endDate can't be null")
    private LocalDate endDate;

    @NotNull(message = "product id can't be null")
    private Long productId;

    @NotNull(message = "discount status can't be null")
    private ProductsStatusEnum discountStatus;

}
