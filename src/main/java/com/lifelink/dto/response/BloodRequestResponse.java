package com.lifelink.dto.response;

import java.time.LocalDate;

import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.RequestStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloodRequestResponse {

    private String id;

    private String requesterName;

    private String patientName;

    private BloodGroup bloodGroup;

    private Integer unitsRequired;

    private String hospitalName;

    private String hospitalAddress;

    private String city;

    private String state;

    private String pincode;

    private LocalDate requiredDate;

    private String contactNumber;

    private String notes;

    private Boolean emergency;

    private RequestStatus status;
}