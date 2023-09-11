package com.example.demo.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseStatusEnum {
    SUCCESS("0"),
    FAILURE("1"),
    ERROR("2");
    private final String statusCode;
}
