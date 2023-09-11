package com.example.demo.dto;

import com.example.demo.enums.ProductsModelsEnum;
import com.example.demo.enums.ProductsNameEnum;
import com.example.demo.enums.ProductsTypesEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppleProductsMapDto {
    @Min(value = 1,message = "id must be at least ,{value}")
    private Long id;

    @NotNull(message = "product name can't be null")
    private ProductsNameEnum productName;

    @NotNull(message = "product type can't be null")
    private ProductsTypesEnum productType;

    @NotNull(message = "product model can't be null")
    private ProductsModelsEnum productModel;

    @NotNull(message = "date of issue can't be null")
    private LocalDate dateOfIssue;

    @NotNull(message = "date of issue can't be null")
    private LocalDateTime creationDate;

    @NotNull(message = "date of issue can't be null")
    private LocalDateTime updatedDate;
}
