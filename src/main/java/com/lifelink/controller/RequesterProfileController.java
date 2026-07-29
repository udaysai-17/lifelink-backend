package com.lifelink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lifelink.common.ApiResponse;
import com.lifelink.dto.request.CreateRequesterProfileRequest;
import com.lifelink.dto.request.UpdateRequesterProfileRequest;
import com.lifelink.dto.response.RequesterProfileResponse;
import com.lifelink.service.RequesterProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/requester/profile")
@RequiredArgsConstructor
@Validated
public class RequesterProfileController {

	private final RequesterProfileService requesterProfileService;

	@PostMapping
	public ResponseEntity<ApiResponse<RequesterProfileResponse>> createProfile(
			@Valid @RequestBody CreateRequesterProfileRequest request) {

		RequesterProfileResponse response = requesterProfileService.createProfile(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<RequesterProfileResponse>builder()
				.success(true).message("Requester profile created successfully").data(response).build());
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<RequesterProfileResponse>> getMyProfile() {

		RequesterProfileResponse response = requesterProfileService.getMyProfile();

		return ResponseEntity.ok(ApiResponse.<RequesterProfileResponse>builder().success(true)
				.message("Requester profile fetched successfully").data(response).build());
	}

	@PutMapping
	public ResponseEntity<ApiResponse<RequesterProfileResponse>> updateProfile(
			@RequestBody UpdateRequesterProfileRequest request) {

		RequesterProfileResponse response = requesterProfileService.updateProfile(request);

		return ResponseEntity.ok(ApiResponse.<RequesterProfileResponse>builder().success(true)
				.message("Requester profile updated successfully").data(response).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<RequesterProfileResponse>> getProfileById(@PathVariable String id) {

		RequesterProfileResponse response = requesterProfileService.getProfileById(id);

		return ResponseEntity.ok(ApiResponse.<RequesterProfileResponse>builder().success(true)
				.message("Requester profile fetched successfully").data(response).build());
	}
}