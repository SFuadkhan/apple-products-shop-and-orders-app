package com.example.apple_orders_ms.util;

import com.example.apple_orders_ms.config.SecurityConfig;
import com.example.apple_orders_ms.exception.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.apple_orders_ms.enums.ResponseStatusEnum.SUCCESS;

@Component
@Slf4j
@SuppressWarnings("PMD")
@RequiredArgsConstructor
public class JwtTokenChecker {
    private final SecurityConfig securityConfig;

    private void validateJwtToken(String authToken, String signingKey) {
        log.info("Start validate JWT Token: {}", authToken);
        var message = SUCCESS.name();
        try {
            Jwts.parser().setSigningKey(signingKey).parseClaimsJws(authToken);
        } catch (SignatureException e) {
            message = "Invalid JWT signature: " + e.getMessage();
        } catch (MalformedJwtException e) {
            message = "Invalid JWT token: " + e.getMessage();
        } catch (ExpiredJwtException e) {
            message = "JWT token is expired: " + e.getMessage();
        } catch (UnsupportedJwtException e) {
            message = "JWT token is unsupported: " + e.getMessage();
        } catch (IllegalArgumentException e) {
            message = "JWT claims string is empty: " + e.getMessage();
        }
        if (!SUCCESS.name().equals(message)) {
            throw new UnauthorizedException(message);
        }
    }

    public void validateJwtToken(String authToken) {
        validateJwtToken(authToken, securityConfig.getSecret());
    }
}
