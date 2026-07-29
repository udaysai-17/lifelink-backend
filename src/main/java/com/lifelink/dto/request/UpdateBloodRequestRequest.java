package com.lifelink.dto.request;

import java.time.LocalDate;

import com.lifelink.enums.BloodGroup;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateBloodRequestRequest {

    private String patientName;

    private BloodGroup bloodGroup;

    @Min(value = 1, message = "Units required must be at least 1")
    private Integer unitsRequired;

    private String hospitalName;

    private String hospitalAddress;

    private String city;

    private String state;

    private String pincode;

    @FutureOrPresent(message = "Required date cannot be in the past")
    private LocalDate requiredDate;

    private String contactNumber;

    private String notes;

    private Boolean emergency;
}