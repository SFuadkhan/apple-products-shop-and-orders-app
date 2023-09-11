package com.example.apple_orders_ms.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
        log.info("Needed message is set to exception");
    }
}
