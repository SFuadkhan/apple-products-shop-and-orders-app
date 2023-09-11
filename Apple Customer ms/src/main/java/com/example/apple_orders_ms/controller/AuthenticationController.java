package com.example.apple_orders_ms.controller;

import com.example.apple_orders_ms.dto.ResponseDto;
import com.example.apple_orders_ms.dto.auth.AuthenticationRequest;
import com.example.apple_orders_ms.dto.auth.AuthenticationResponse;
import com.example.apple_orders_ms.service.AuthenticationService;
import com.example.apple_orders_ms.util.ResponseCreator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Methods for Authentication")
@Slf4j
@Validated
public class AuthenticationController {
    private final AuthenticationService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success Authentication", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Failed Authentication", content = @Content),
            @ApiResponse(responseCode = "404", description = "Failed Authentication", content = @Content)
    })

    @PostMapping("/authentication")
    @Operation(summary = "Authentication method", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseDto<AuthenticationResponse> authentication(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        log.info("Authentication method called, requestBody: {}", request);
        return ResponseCreator.createSuccessResponse(this.service.checkAuthenticationForJwt(request));
    }
}