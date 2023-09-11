package com.example.apple_orders_ms.dto;


import com.example.apple_orders_ms.enums.DevicesEnum;
import com.example.apple_orders_ms.enums.ModelsEnum;
import com.example.apple_orders_ms.enums.OrderStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppleOrderDto{
    private Long id;

    @NotBlank(message = "The name is not provided")
    @Size(min = 2, max = 20,message = "Invalid name")
    private String name;

    @Size(min = 2, max = 20,message = "Invalid surname")
    @NotBlank
    private String surname;

    @NotNull(message = "Device can't be null")
    private DevicesEnum device;

    @NotNull(message = "Model can't be null")
    private ModelsEnum model;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull(message = "Status can't be null")
    private OrderStatusEnum orderStatus;

    @NotNull(message = "CustomerId must not be null")
    private Long customerId;
}
