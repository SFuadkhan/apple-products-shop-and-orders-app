package com.example.demo.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppleProductsDto {
    @Min(value = 1,message = "id must be at least ,{value}")
    private Long id;

    @NotNull(message = "price can't be null")
    @Min(value = 100,message = "price must be at least, {value}")
    private Integer price;

    @NotNull(message = "products in stock can't be null")
    @Min(value = 0, message = "stock must be non-negative")
    private Integer productsInStock;

    @NotNull(message = "created at stock can't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "updated at stock can't be null")
    private LocalDateTime updatedAt;
}
