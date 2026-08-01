package com.lifelink.service;

import java.util.List;

import com.lifelink.dto.response.BloodRequestResponse;
import com.lifelink.dto.response.DonationResponseDto;

public interface DonationResponseService {

    List<BloodRequestResponse> getMatchingRequests();

    void acceptRequest(String requestId);

    void rejectRequest(String requestId);

    void completeDonation(String requestId);

    List<DonationResponseDto> getMyDonations();

}