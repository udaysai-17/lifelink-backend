package com.lifelink.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;
import com.lifelink.enums.AvailabilityStatus;
import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "donor_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DonorProfile extends BaseEntity {

	@Id
	private String id;

	@DBRef
	private User user;

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

	@Builder.Default
	private AvailabilityStatus availabilityStatus = AvailabilityStatus.AVAILABLE;

	@Builder.Default
	private Boolean verified = false;

	private String profilePhoto;
}