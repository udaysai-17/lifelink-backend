package com.lifelink.mapper;

import org.springframework.stereotype.Component;

import com.lifelink.dto.response.BloodRequestResponse;
import com.lifelink.entity.BloodRequest;

@Component
public class BloodRequestMapper {

    public BloodRequestResponse toResponse(BloodRequest request) {

        return BloodRequestResponse.builder()
                .id(request.getId())
                .requesterName(request.getRequester().getFullName())
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
                .emergency(request.getEmergency())
                .status(request.getStatus())
                .build();
    }
}