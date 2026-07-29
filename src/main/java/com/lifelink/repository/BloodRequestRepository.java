package com.lifelink.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lifelink.entity.BloodRequest;
import com.lifelink.entity.RequesterProfile;
import com.lifelink.enums.RequestStatus;

@Repository
public interface BloodRequestRepository extends MongoRepository<BloodRequest, String> {

    List<BloodRequest> findByRequester(RequesterProfile requester);

    List<BloodRequest> findByStatus(RequestStatus status);

}