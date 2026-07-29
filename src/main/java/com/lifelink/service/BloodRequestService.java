package com.lifelink.service;

import java.util.List;

import com.lifelink.dto.request.CreateBloodRequestRequest;
import com.lifelink.dto.request.UpdateBloodRequestRequest;
import com.lifelink.dto.response.BloodRequestResponse;

public interface BloodRequestService {

    BloodRequestResponse createRequest(CreateBloodRequestRequest request);

    List<BloodRequestResponse> getMyRequests();

    BloodRequestResponse getRequestById(String id);

    BloodRequestResponse updateRequest(String id,
                                       UpdateBloodRequestRequest request);

    void cancelRequest(String id);

    List<BloodRequestResponse> getOpenRequests();
}