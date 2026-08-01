package com.lifelink.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lifelink.dto.response.BloodRequestResponse;
import com.lifelink.dto.response.DonationResponseDto;
import com.lifelink.entity.BloodRequest;
import com.lifelink.entity.DonorProfile;
import com.lifelink.entity.User;
import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.enums.RequestStatus;
import com.lifelink.exception.ResourceNotFoundException;
import com.lifelink.mapper.BloodRequestMapper;
import com.lifelink.repository.BloodRequestRepository;
import com.lifelink.repository.DonationResponseRepository;
import com.lifelink.repository.DonorProfileRepository;
import com.lifelink.repository.UserRepository;
import com.lifelink.service.DonationResponseService;
import com.lifelink.util.BloodCompatibilityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationResponseServiceImpl implements DonationResponseService {

	private final DonationResponseRepository donationResponseRepository;
	private final BloodRequestRepository bloodRequestRepository;
	private final DonorProfileRepository donorProfileRepository;
	private final UserRepository userRepository;
	private final BloodRequestMapper bloodRequestMapper;

	private static final int DONATION_COOLING_DAYS = 90;

	private User getLoggedInUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	private DonorProfile getLoggedInDonor() {

		User user = getLoggedInUser();

		return donorProfileRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("Donor profile not found"));
	}

	private boolean isBloodCompatible(DonorProfile donor, BloodRequest request) {

		return BloodCompatibilityUtil.isCompatible(donor.getBloodGroup(), request.getBloodGroup());
	}

	private boolean isSameCity(DonorProfile donor, BloodRequest request) {

		if (donor.getCity() == null || request.getCity() == null) {
			return false;
		}

		return donor.getCity().equalsIgnoreCase(request.getCity());
	}

	private boolean isDonorAvailable(DonorProfile donor) {

		return donor.getAvailabilityStatus() == AvailabilityStatus.AVAILABLE;
	}

	private boolean isVerifiedDonor(DonorProfile donor) {

		return Boolean.TRUE.equals(donor.getVerified());
	}

	private boolean isCoolingPeriodCompleted(DonorProfile donor) {

		if (donor.getLastDonationDate() == null) {
			return true;
		}

		LocalDate eligibleDate = donor.getLastDonationDate().plusDays(DONATION_COOLING_DAYS);

		return !eligibleDate.isAfter(LocalDate.now());
	}

	private boolean hasAlreadyResponded(DonorProfile donor, BloodRequest request) {

		return donationResponseRepository.existsByBloodRequestAndDonor(request, donor);
	}

	@Override
	public List<BloodRequestResponse> getMatchingRequests() {

		DonorProfile donor = getLoggedInDonor();

		List<BloodRequest> openRequests = bloodRequestRepository.findByStatus(RequestStatus.OPEN);

		return openRequests.stream()

				.filter(request -> isBloodCompatible(donor, request))

				.filter(request -> isSameCity(donor, request))

				.filter(request -> isDonorAvailable(donor))

				.filter(request -> isVerifiedDonor(donor))

				.filter(request -> isCoolingPeriodCompleted(donor))

				.filter(request -> !hasAlreadyResponded(donor, request))

				.map(bloodRequestMapper::toResponse)

				.toList();
	}

	@Override
	public void acceptRequest(String requestId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void rejectRequest(String requestId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public void completeDonation(String requestId) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	@Override
	public List<DonationResponseDto> getMyDonations() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}