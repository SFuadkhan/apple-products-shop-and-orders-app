package com.example.apple_orders_ms;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Apple Products' Orders API",
        description = "Apple Products' Orders, registration and authentication Methods",
        version = "V1.3.0"))
public class AppleCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppleCustomerApplication.class, args);
    }
}
