package com.example.apple_orders_ms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "prop.jwt")
@Data
public class SecurityConfig {
    private String username;
    private String password;
    private Integer expiration;
    private String secret;
}

