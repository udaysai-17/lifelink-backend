package com.lifelink.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;
import com.lifelink.enums.BloodGroup;
import com.lifelink.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "blood_requests")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BloodRequest extends BaseEntity {

	@Id
	private String id;

	@DBRef
	private RequesterProfile requester;

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

	@Builder.Default
	private RequestStatus status = RequestStatus.OPEN;

	@Builder.Default
	private Boolean emergency = false;
}