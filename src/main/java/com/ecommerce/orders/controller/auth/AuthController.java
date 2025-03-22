package com.ecommerce.orders.controller.auth;

import com.ecommerce.orders.dto.security.AuthRequest;
import com.ecommerce.orders.dto.security.AuthResponse;
import com.ecommerce.orders.service.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user login and token refresh")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Authenticate user", description = "Logs in a user and returns access and refresh tokens")
  @ApiResponse(responseCode = "200", description = "Successful authentication")
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
    return ResponseEntity.ok(authService.authenticate(request));
  }

  @Operation(summary = "Refresh JWT token", description = "Uses the refresh token to generate a new access token")
  @ApiResponse(responseCode = "200", description = "New access token generated")
  @PostMapping("/refresh")
  public ResponseEntity<AuthResponse> refresh(@Valid @RequestBody String refreshToken) {
    return ResponseEntity.ok(authService.refresh(refreshToken));
  }
}