package com.lifelink.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;
import com.lifelink.enums.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "donation_responses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DonationResponse extends BaseEntity {

	@Id
	private String id;

	@DBRef
	private BloodRequest bloodRequest;

	@DBRef
	private DonorProfile donor;

	@Builder.Default
	private ResponseStatus responseStatus = ResponseStatus.PENDING;

	private LocalDateTime responseTime;

	@Builder.Default
	private Boolean donationCompleted = false;
}