package com.lifelink.dto.response;

import java.time.LocalDate;

import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.Gender;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonorProfileResponse {

	private String id;

	private String fullName;

	private String email;

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

	private Boolean verified;

	private String profilePhoto;
}