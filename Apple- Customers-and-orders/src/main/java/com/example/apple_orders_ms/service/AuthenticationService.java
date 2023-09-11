package com.example.apple_orders_ms.service;

import com.example.apple_orders_ms.dto.auth.AuthenticationRequest;
import com.example.apple_orders_ms.dto.auth.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse checkAuthenticationForJwt(AuthenticationRequest request);

}
