package com.lifelink.dto.response;

import java.time.LocalDateTime;

import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.ResponseStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonationResponseDto {

    private String id;

    private String donorName;

    private String patientName;

    private BloodGroup bloodGroup;

    private String hospitalName;

    private LocalDateTime responseTime;

    private ResponseStatus responseStatus;

    private Boolean donationCompleted;
}