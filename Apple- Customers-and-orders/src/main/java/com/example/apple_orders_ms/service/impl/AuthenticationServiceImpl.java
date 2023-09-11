package com.example.apple_orders_ms.service.impl;

import com.example.apple_orders_ms.config.SecurityConfig;
import com.example.apple_orders_ms.dto.auth.AuthenticationRequest;
import com.example.apple_orders_ms.dto.auth.AuthenticationResponse;
import com.example.apple_orders_ms.exception.ForbiddenException;
import com.example.apple_orders_ms.service.AuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final SecurityConfig securityConfig;


    @Override
    public AuthenticationResponse checkAuthenticationForJwt(AuthenticationRequest request) {

        boolean isUserName = !securityConfig.getUsername().equals(request.getUsername());
        boolean isPassword = !securityConfig.getPassword().equals(request.getPassword());

        if (isUserName || isPassword) {

            var message = "Username or password was provided incorrectly";
            log.warn(message);
            throw new ForbiddenException(message);
        }
        log.info("Username and password is correctly provided, authentication token generation started");
        var authToken = generateJwtToken(request.getUsername());
        log.info("Authentication token generated: {}", authToken);
        return new AuthenticationResponse(authToken);
    }

    private String generateJwtToken(String jwtUsername) {
        return Jwts.builder()
                .setSubject(jwtUsername)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + securityConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, securityConfig.getSecret())
                .compact();
    }
}