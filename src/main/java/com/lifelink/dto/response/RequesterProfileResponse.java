package com.lifelink.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequesterProfileResponse {

    private String id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private String profilePhoto;
}