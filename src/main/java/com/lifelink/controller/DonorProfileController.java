package com.lifelink.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lifelink.common.ApiResponse;
import com.lifelink.dto.request.CreateDonorProfileRequest;
import com.lifelink.dto.request.UpdateDonorProfileRequest;
import com.lifelink.dto.response.DonorProfileResponse;
import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.service.DonorProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/donor/profile")
@RequiredArgsConstructor
@Validated
public class DonorProfileController {

	private final DonorProfileService donorProfileService;

	@PostMapping
	public ResponseEntity<ApiResponse<DonorProfileResponse>> createProfile(
			@Valid @RequestBody CreateDonorProfileRequest request) {

		DonorProfileResponse response = donorProfileService.createProfile(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<DonorProfileResponse>builder().success(true)
				.message("Donor profile created successfully").data(response).build());
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<DonorProfileResponse>> getMyProfile() {

		DonorProfileResponse response = donorProfileService.getMyProfile();

		return ResponseEntity.ok(ApiResponse.<DonorProfileResponse>builder().success(true)
				.message("Donor profile fetched successfully").data(response).build());
	}

	@PutMapping
	public ResponseEntity<ApiResponse<DonorProfileResponse>> updateProfile(
			@RequestBody UpdateDonorProfileRequest request) {

		DonorProfileResponse response = donorProfileService.updateProfile(request);

		return ResponseEntity.ok(ApiResponse.<DonorProfileResponse>builder().success(true)
				.message("Donor profile updated successfully").data(response).build());
	}

	@PatchMapping("/availability")
	public ResponseEntity<ApiResponse<DonorProfileResponse>> updateAvailability(
			@RequestParam AvailabilityStatus status) {

		DonorProfileResponse response = donorProfileService.updateAvailability(status);

		return ResponseEntity.ok(ApiResponse.<DonorProfileResponse>builder().success(true)
				.message("Availability updated successfully").data(response).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<DonorProfileResponse>> getProfileById(@PathVariable String id) {

		DonorProfileResponse response = donorProfileService.getProfileById(id);

		return ResponseEntity.ok(ApiResponse.<DonorProfileResponse>builder().success(true)
				.message("Donor profile fetched successfully").data(response).build());
	}
}