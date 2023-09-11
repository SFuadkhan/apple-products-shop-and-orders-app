package com.example.apple_orders_ms.util;

import com.example.apple_orders_ms.dto.ResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.apple_orders_ms.enums.ResponseStatusEnum.SUCCESS;

@NoArgsConstructor
@Slf4j
public final class ResponseCreator {
    public static <T> ResponseDto<T> createSuccessResponse(T body) {
        log.info("method to create response is called {}",body);
        return new ResponseDto<>(SUCCESS.getStatusCode(), SUCCESS.name(),  body);
    }
}
