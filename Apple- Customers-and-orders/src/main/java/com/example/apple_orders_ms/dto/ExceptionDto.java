package com.example.apple_orders_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionDto {
    private Integer code;
    private String message;
}
