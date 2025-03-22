package com.ecommerce.orders.service.security;

import com.ecommerce.orders.dto.security.AuthRequest;
import com.ecommerce.orders.dto.security.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Service", description = "Handles authentication and token refresh logic")
public interface AuthService {

  @Operation(summary = "Authenticate user", description = "Validates user credentials and returns JWT tokens")
  AuthResponse authenticate(AuthRequest request);

  @Operation(summary = "Refresh token", description = "Generates a new access token using a valid refresh token")
  AuthResponse refresh(String refreshToken);

}