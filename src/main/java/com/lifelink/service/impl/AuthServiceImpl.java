package com.lifelink.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lifelink.dto.request.LoginRequest;
import com.lifelink.dto.request.RegisterRequest;
import com.lifelink.dto.response.AuthResponse;
import com.lifelink.entity.User;
import com.lifelink.exception.EmailAlreadyExistsException;
import com.lifelink.exception.InvalidCredentialsException;
import com.lifelink.exception.ResourceNotFoundException;
import com.lifelink.repository.UserRepository;
import com.lifelink.security.CustomUserDetails;
import com.lifelink.service.AuthService;
import com.lifelink.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Override
	public AuthResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists");
		}

		User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).accountEnabled(true).build();

		userRepository.save(user);

		return AuthResponse.builder().userId(user.getId()).email(user.getEmail()).role(user.getRole())
				.message("User registered successfully").build();
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (Exception ex) {
			throw new InvalidCredentialsException("Invalid email or password");
		}

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		String token = jwtService.generateToken(new CustomUserDetails(user));

		return AuthResponse.builder().token(token).userId(user.getId()).email(user.getEmail()).role(user.getRole())
				.message("Login successful").build();
	}
}