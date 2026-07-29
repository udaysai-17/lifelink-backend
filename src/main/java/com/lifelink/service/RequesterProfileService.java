package com.lifelink.service;

import com.lifelink.dto.request.CreateRequesterProfileRequest;
import com.lifelink.dto.request.UpdateRequesterProfileRequest;
import com.lifelink.dto.response.RequesterProfileResponse;

public interface RequesterProfileService {

    RequesterProfileResponse createProfile(CreateRequesterProfileRequest request);

    RequesterProfileResponse getMyProfile();

    RequesterProfileResponse updateProfile(UpdateRequesterProfileRequest request);

    RequesterProfileResponse getProfileById(String id);
}