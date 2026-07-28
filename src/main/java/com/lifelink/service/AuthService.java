package com.lifelink.service;

import com.lifelink.dto.request.LoginRequest;
import com.lifelink.dto.request.RegisterRequest;
import com.lifelink.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}