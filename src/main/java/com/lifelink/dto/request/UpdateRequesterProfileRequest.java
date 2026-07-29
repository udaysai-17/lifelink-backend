package com.lifelink.dto.request;

import lombok.Data;

@Data
public class UpdateRequesterProfileRequest {

    private String fullName;

    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;
}