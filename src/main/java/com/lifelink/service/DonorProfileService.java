package com.lifelink.service;

import com.lifelink.dto.request.CreateDonorProfileRequest;
import com.lifelink.dto.request.UpdateDonorProfileRequest;
import com.lifelink.dto.response.DonorProfileResponse;
import com.lifelink.enums.AvailabilityStatus;

public interface DonorProfileService {

	DonorProfileResponse createProfile(CreateDonorProfileRequest request);

	DonorProfileResponse getMyProfile();

	DonorProfileResponse updateProfile(UpdateDonorProfileRequest request);

	DonorProfileResponse updateAvailability(AvailabilityStatus status);

	DonorProfileResponse getProfileById(String id);
}