package com.lifelink.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lifelink.dto.request.CreateRequesterProfileRequest;
import com.lifelink.dto.request.UpdateRequesterProfileRequest;
import com.lifelink.dto.response.RequesterProfileResponse;
import com.lifelink.entity.RequesterProfile;
import com.lifelink.entity.User;
import com.lifelink.exception.ProfileAlreadyExistsException;
import com.lifelink.exception.ResourceNotFoundException;
import com.lifelink.repository.RequesterProfileRepository;
import com.lifelink.repository.UserRepository;
import com.lifelink.service.RequesterProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequesterProfileServiceImpl implements RequesterProfileService {

    private final RequesterProfileRepository requesterProfileRepository;
    private final UserRepository userRepository;

    private RequesterProfileResponse mapToResponse(RequesterProfile requesterProfile) {

        return RequesterProfileResponse.builder()
                .id(requesterProfile.getId())
                .fullName(requesterProfile.getFullName())
                .email(requesterProfile.getUser().getEmail())
                .phoneNumber(requesterProfile.getPhoneNumber())
                .address(requesterProfile.getAddress())
                .city(requesterProfile.getCity())
                .state(requesterProfile.getState())
                .pincode(requesterProfile.getPincode())
                .profilePhoto(requesterProfile.getProfilePhoto())
                .build();
    }
    
    
    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }
    
    
    @Override
    public RequesterProfileResponse createProfile(CreateRequesterProfileRequest request) {

        User user = getLoggedInUser();

        if (requesterProfileRepository.existsByUser(user)) {
            throw new ProfileAlreadyExistsException("Requester profile already exists");
        }

        RequesterProfile requesterProfile = RequesterProfile.builder()
                .user(user)
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .build();

        user.setProfileCompleted(true);

        userRepository.save(user);

        requesterProfile = requesterProfileRepository.save(requesterProfile);

        return mapToResponse(requesterProfile);
    }
    
    
    @Override
    public RequesterProfileResponse getMyProfile() {

        User user = getLoggedInUser();

        RequesterProfile requesterProfile = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        return mapToResponse(requesterProfile);
    }
    
    
    @Override
    public RequesterProfileResponse updateProfile(UpdateRequesterProfileRequest request) {

        User user = getLoggedInUser();

        RequesterProfile requesterProfile = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        if (request.getFullName() != null) {
            requesterProfile.setFullName(request.getFullName());
        }

        if (request.getPhoneNumber() != null) {
            requesterProfile.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAddress() != null) {
            requesterProfile.setAddress(request.getAddress());
        }

        if (request.getCity() != null) {
            requesterProfile.setCity(request.getCity());
        }

        if (request.getState() != null) {
            requesterProfile.setState(request.getState());
        }

        if (request.getPincode() != null) {
            requesterProfile.setPincode(request.getPincode());
        }

        requesterProfile = requesterProfileRepository.save(requesterProfile);

        return mapToResponse(requesterProfile);
    }
    
    
    @Override
    public RequesterProfileResponse getProfileById(String id) {

        RequesterProfile requesterProfile = requesterProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        return mapToResponse(requesterProfile);
    }
}