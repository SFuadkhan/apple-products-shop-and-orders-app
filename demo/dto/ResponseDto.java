package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {

    private String statusCode;


    private String statusDesc;

    @NotNull(message = "data can't be null")
    private T data;

}
