package com.example.demo.util;

import com.example.demo.dto.ResponseDto;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.demo.enums.ResponseStatusEnum.SUCCESS;

@NoArgsConstructor
@Slf4j
public final class ResponseCreator {
    public static <T> ResponseDto<T> createSuccessResponse(T body) {
        log.info("method transforming any data into Response Dto is called with bode: {}",body);
        return new ResponseDto<>(SUCCESS.getStatusCode(), SUCCESS.name(),  body);
    }
}
