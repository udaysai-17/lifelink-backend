package com.lifelink.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lifelink.dto.request.CreateBloodRequestRequest;
import com.lifelink.dto.request.UpdateBloodRequestRequest;
import com.lifelink.dto.response.BloodRequestResponse;
import com.lifelink.entity.BloodRequest;
import com.lifelink.entity.RequesterProfile;
import com.lifelink.entity.User;
import com.lifelink.enums.RequestStatus;
import com.lifelink.exception.ResourceNotFoundException;
import com.lifelink.mapper.BloodRequestMapper;
import com.lifelink.repository.BloodRequestRepository;
import com.lifelink.repository.RequesterProfileRepository;
import com.lifelink.repository.UserRepository;
import com.lifelink.service.BloodRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BloodRequestServiceImpl implements BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;
    private final RequesterProfileRepository requesterProfileRepository;
    private final UserRepository userRepository;
    private final BloodRequestMapper bloodRequestMapper;

    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    private List<BloodRequestResponse> mapToResponseList(List<BloodRequest> requests) {

        return requests.stream()
                .map(bloodRequestMapper::toResponse)
                .toList();
    }

    @Override
    public BloodRequestResponse createRequest(CreateBloodRequestRequest request) {

        User user = getLoggedInUser();

        RequesterProfile requester = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        BloodRequest bloodRequest = BloodRequest.builder()
                .requester(requester)
                .patientName(request.getPatientName())
                .bloodGroup(request.getBloodGroup())
                .unitsRequired(request.getUnitsRequired())
                .hospitalName(request.getHospitalName())
                .hospitalAddress(request.getHospitalAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .requiredDate(request.getRequiredDate())
                .contactNumber(request.getContactNumber())
                .notes(request.getNotes())
                .emergency(request.getEmergency() != null
                        ? request.getEmergency()
                        : false)
                .status(RequestStatus.OPEN)
                .build();

        bloodRequest = bloodRequestRepository.save(bloodRequest);

        return bloodRequestMapper.toResponse(bloodRequest);
    }

    @Override
    public List<BloodRequestResponse> getMyRequests() {

        User user = getLoggedInUser();

        RequesterProfile requester = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        List<BloodRequest> requests =
                bloodRequestRepository.findByRequester(requester);

        return mapToResponseList(requests);
    }

    @Override
    public BloodRequestResponse getRequestById(String id) {

        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Blood request not found"));

        return bloodRequestMapper.toResponse(bloodRequest);
    }

    @Override
    public BloodRequestResponse updateRequest(
            String id,
            UpdateBloodRequestRequest request) {

        User user = getLoggedInUser();

        RequesterProfile requester = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Blood request not found"));

        if (!bloodRequest.getRequester().getId().equals(requester.getId())) {
            throw new IllegalStateException(
                    "You are not allowed to update this request");
        }

        if (bloodRequest.getStatus() == RequestStatus.COMPLETED
                || bloodRequest.getStatus() == RequestStatus.CANCELLED) {
            throw new IllegalStateException(
                    "Request cannot be updated");
        }

        if (request.getPatientName() != null)
            bloodRequest.setPatientName(request.getPatientName());

        if (request.getBloodGroup() != null)
            bloodRequest.setBloodGroup(request.getBloodGroup());

        if (request.getUnitsRequired() != null)
            bloodRequest.setUnitsRequired(request.getUnitsRequired());

        if (request.getHospitalName() != null)
            bloodRequest.setHospitalName(request.getHospitalName());

        if (request.getHospitalAddress() != null)
            bloodRequest.setHospitalAddress(request.getHospitalAddress());

        if (request.getCity() != null)
            bloodRequest.setCity(request.getCity());

        if (request.getState() != null)
            bloodRequest.setState(request.getState());

        if (request.getPincode() != null)
            bloodRequest.setPincode(request.getPincode());

        if (request.getRequiredDate() != null)
            bloodRequest.setRequiredDate(request.getRequiredDate());

        if (request.getContactNumber() != null)
            bloodRequest.setContactNumber(request.getContactNumber());

        if (request.getNotes() != null)
            bloodRequest.setNotes(request.getNotes());

        if (request.getEmergency() != null)
            bloodRequest.setEmergency(request.getEmergency());

        bloodRequest = bloodRequestRepository.save(bloodRequest);

        return bloodRequestMapper.toResponse(bloodRequest);
    }

    @Override
    public void cancelRequest(String id) {

        User user = getLoggedInUser();

        RequesterProfile requester = requesterProfileRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Requester profile not found"));

        BloodRequest bloodRequest = bloodRequestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Blood request not found"));

        if (!bloodRequest.getRequester().getId().equals(requester.getId())) {
            throw new IllegalStateException(
                    "You are not allowed to cancel this request");
        }

        bloodRequest.setStatus(RequestStatus.CANCELLED);

        bloodRequestRepository.save(bloodRequest);
    }

    @Override
    public List<BloodRequestResponse> getOpenRequests() {

        List<BloodRequest> requests =
                bloodRequestRepository.findByStatus(RequestStatus.OPEN);

        return mapToResponseList(requests);
    }
}