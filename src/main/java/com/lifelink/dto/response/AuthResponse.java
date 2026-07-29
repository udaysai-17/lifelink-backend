package com.lifelink.dto.response;

import com.lifelink.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String token;

	private String userId;

	private String email;

	private Role role;

	private String message;
	private Boolean profileCompleted;
}