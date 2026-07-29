package com.lifelink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lifelink.common.ApiResponse;
import com.lifelink.dto.request.CreateBloodRequestRequest;
import com.lifelink.dto.request.UpdateBloodRequestRequest;
import com.lifelink.dto.response.BloodRequestResponse;
import com.lifelink.service.BloodRequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@Validated
public class BloodRequestController {

	private final BloodRequestService bloodRequestService;

	@PostMapping
	public ResponseEntity<ApiResponse<BloodRequestResponse>> createRequest(
			@Valid @RequestBody CreateBloodRequestRequest request) {

		BloodRequestResponse response = bloodRequestService.createRequest(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<BloodRequestResponse>builder().success(true)
				.message("Blood request created successfully").data(response).build());
	}

	@GetMapping("/my")
	public ResponseEntity<ApiResponse<List<BloodRequestResponse>>> getMyRequests() {

		List<BloodRequestResponse> response = bloodRequestService.getMyRequests();

		return ResponseEntity.ok(ApiResponse.<List<BloodRequestResponse>>builder().success(true)
				.message("Blood requests fetched successfully").data(response).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BloodRequestResponse>> getRequestById(@PathVariable String id) {

		BloodRequestResponse response = bloodRequestService.getRequestById(id);

		return ResponseEntity.ok(ApiResponse.<BloodRequestResponse>builder().success(true)
				.message("Blood request fetched successfully").data(response).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<BloodRequestResponse>> updateRequest(@PathVariable String id,
			@RequestBody UpdateBloodRequestRequest request) {

		BloodRequestResponse response = bloodRequestService.updateRequest(id, request);

		return ResponseEntity.ok(ApiResponse.<BloodRequestResponse>builder().success(true)
				.message("Blood request updated successfully").data(response).build());
	}

	@PatchMapping("/{id}/cancel")
	public ResponseEntity<ApiResponse<Void>> cancelRequest(@PathVariable String id) {

		bloodRequestService.cancelRequest(id);

		return ResponseEntity
				.ok(ApiResponse.<Void>builder().success(true).message("Blood request cancelled successfully").build());
	}

	@GetMapping("/open")
	public ResponseEntity<ApiResponse<List<BloodRequestResponse>>> getOpenRequests() {

		List<BloodRequestResponse> response = bloodRequestService.getOpenRequests();

		return ResponseEntity.ok(ApiResponse.<List<BloodRequestResponse>>builder().success(true)
				.message("Open blood requests fetched successfully").data(response).build());
	}
}