package com.example.apple_orders_ms.exception;

import java.io.Serial;


public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 12345687917L;
}