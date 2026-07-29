package com.lifelink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lifelink.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "requester_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RequesterProfile extends BaseEntity {

	@Id
	private String id;

	@DBRef
	private User user;

	private String fullName;

	private String phoneNumber;

	private String address;

	private String city;

	private String state;

	private String pincode;

	private String profilePhoto;
}