package com.lifelink.dto.request;

import java.time.LocalDate;

import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDonorProfileRequest {

	@NotBlank(message = "Full name is required")
	private String fullName;

	@NotBlank(message = "Phone number is required")
	private String phoneNumber;

	@NotNull(message = "Blood group is required")
	private BloodGroup bloodGroup;

	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotNull(message = "Date of birth is required")
	private LocalDate dateOfBirth;

	@NotNull(message = "Weight is required")
	private Double weight;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "State is required")
	private String state;

	@NotBlank(message = "Pincode is required")
	private String pincode;

	private LocalDate lastDonationDate;

	private AvailabilityStatus availabilityStatus;
}