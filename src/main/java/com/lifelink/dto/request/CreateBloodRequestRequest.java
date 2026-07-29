package com.lifelink.dto.request;

import java.time.LocalDate;

import com.lifelink.enums.BloodGroup;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBloodRequestRequest {

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;

    @NotNull(message = "Units required is mandatory")
    @Min(value = 1, message = "Units required must be at least 1")
    private Integer unitsRequired;

    @NotBlank(message = "Hospital name is required")
    private String hospitalName;

    @NotBlank(message = "Hospital address is required")
    private String hospitalAddress;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    @NotNull(message = "Required date is mandatory")
    @FutureOrPresent(message = "Required date cannot be in the past")
    private LocalDate requiredDate;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    private String notes;

    private Boolean emergency;
}