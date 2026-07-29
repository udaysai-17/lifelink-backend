package com.lifelink.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lifelink.dto.request.CreateDonorProfileRequest;
import com.lifelink.dto.request.UpdateDonorProfileRequest;
import com.lifelink.dto.response.DonorProfileResponse;
import com.lifelink.entity.DonorProfile;
import com.lifelink.entity.User;
import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.exception.ProfileAlreadyExistsException;
import com.lifelink.exception.ResourceNotFoundException;
import com.lifelink.repository.DonorProfileRepository;
import com.lifelink.repository.UserRepository;
import com.lifelink.service.DonorProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonorProfileServiceImpl implements DonorProfileService {

	private final DonorProfileRepository donorProfileRepository;
	private final UserRepository userRepository;

	private DonorProfileResponse mapToResponse(DonorProfile donorProfile) {

		return DonorProfileResponse.builder().id(donorProfile.getId()).fullName(donorProfile.getFullName())
				.email(donorProfile.getUser().getEmail()).phoneNumber(donorProfile.getPhoneNumber())
				.bloodGroup(donorProfile.getBloodGroup()).gender(donorProfile.getGender())
				.dateOfBirth(donorProfile.getDateOfBirth()).weight(donorProfile.getWeight())
				.address(donorProfile.getAddress()).city(donorProfile.getCity()).state(donorProfile.getState())
				.pincode(donorProfile.getPincode()).lastDonationDate(donorProfile.getLastDonationDate())
				.availabilityStatus(donorProfile.getAvailabilityStatus()).verified(donorProfile.getVerified())
				.profilePhoto(donorProfile.getProfilePhoto()).build();
	}

	private User getLoggedInUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public DonorProfileResponse createProfile(CreateDonorProfileRequest request) {

		User user = getLoggedInUser();

		if (donorProfileRepository.existsByUser(user)) {
			throw new ProfileAlreadyExistsException("Donor profile already exists");
		}

		DonorProfile donorProfile = DonorProfile.builder().user(user).fullName(request.getFullName())
				.phoneNumber(request.getPhoneNumber()).bloodGroup(request.getBloodGroup()).gender(request.getGender())
				.dateOfBirth(request.getDateOfBirth()).weight(request.getWeight()).address(request.getAddress())
				.city(request.getCity()).state(request.getState()).pincode(request.getPincode())
				.lastDonationDate(request.getLastDonationDate())
				.availabilityStatus(request.getAvailabilityStatus() != null ? request.getAvailabilityStatus()
						: AvailabilityStatus.AVAILABLE)
				.build();

		user.setProfileCompleted(true);

		userRepository.save(user);

		donorProfile = donorProfileRepository.save(donorProfile);

		return mapToResponse(donorProfile);
	}

	@Override
	public DonorProfileResponse getMyProfile() {

		User user = getLoggedInUser();

		DonorProfile donorProfile = donorProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Donor profile not found"));

		return mapToResponse(donorProfile);
	}

	@Override
	public DonorProfileResponse updateProfile(UpdateDonorProfileRequest request) {

		User user = getLoggedInUser();

		DonorProfile donorProfile = donorProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Donor profile not found"));

		if (request.getFullName() != null) {
			donorProfile.setFullName(request.getFullName());
		}

		if (request.getPhoneNumber() != null) {
			donorProfile.setPhoneNumber(request.getPhoneNumber());
		}

		if (request.getBloodGroup() != null) {
			donorProfile.setBloodGroup(request.getBloodGroup());
		}

		if (request.getGender() != null) {
			donorProfile.setGender(request.getGender());
		}

		if (request.getDateOfBirth() != null) {
			donorProfile.setDateOfBirth(request.getDateOfBirth());
		}

		if (request.getWeight() != null) {
			donorProfile.setWeight(request.getWeight());
		}

		if (request.getAddress() != null) {
			donorProfile.setAddress(request.getAddress());
		}

		if (request.getCity() != null) {
			donorProfile.setCity(request.getCity());
		}

		if (request.getState() != null) {
			donorProfile.setState(request.getState());
		}

		if (request.getPincode() != null) {
			donorProfile.setPincode(request.getPincode());
		}

		if (request.getLastDonationDate() != null) {
			donorProfile.setLastDonationDate(request.getLastDonationDate());
		}

		if (request.getAvailabilityStatus() != null) {
			donorProfile.setAvailabilityStatus(request.getAvailabilityStatus());
		}

		donorProfile = donorProfileRepository.save(donorProfile);

		return mapToResponse(donorProfile);
	}

	@Override
	public DonorProfileResponse updateAvailability(AvailabilityStatus status) {

		User user = getLoggedInUser();

		DonorProfile donorProfile = donorProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Donor profile not found"));

		donorProfile.setAvailabilityStatus(status);

		donorProfile = donorProfileRepository.save(donorProfile);

		return mapToResponse(donorProfile);
	}

	@Override
	public DonorProfileResponse getProfileById(String id) {

		DonorProfile donorProfile = donorProfileRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Donor profile not found"));

		return mapToResponse(donorProfile);
	}
}