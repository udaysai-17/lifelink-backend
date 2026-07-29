package com.lifelink.dto.request;

import java.time.LocalDate;

import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.Gender;

import lombok.Data;

@Data
public class UpdateDonorProfileRequest {

    private String fullName;

    private String phoneNumber;

    private BloodGroup bloodGroup;

    private Gender gender;

    private LocalDate dateOfBirth;

    private Double weight;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private LocalDate lastDonationDate;

    private AvailabilityStatus availabilityStatus;
}