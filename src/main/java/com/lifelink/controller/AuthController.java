package com.lifelink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lifelink.common.ApiResponse;
import com.lifelink.dto.request.LoginRequest;
import com.lifelink.dto.request.RegisterRequest;
import com.lifelink.dto.response.AuthResponse;
import com.lifelink.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {

		AuthResponse response = authService.register(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<AuthResponse>builder().success(true)
				.message("Registration Successful").data(response).build());
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {

		AuthResponse response = authService.login(request);

		return ResponseEntity.ok(
				ApiResponse.<AuthResponse>builder().success(true).message("Login Successful").data(response).build());
	}
}