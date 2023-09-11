package com.example.apple_orders_ms.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Builder
public class AuthenticationRequest {

    @NotNull(message = "The username is not provided")
    private String username;

    @NotNull(message = "The password is not provided")
    private String password;
}
