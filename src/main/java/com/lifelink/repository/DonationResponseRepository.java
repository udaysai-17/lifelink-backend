package com.lifelink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lifelink.entity.BloodRequest;
import com.lifelink.entity.DonationResponse;
import com.lifelink.entity.DonorProfile;

@Repository
public interface DonationResponseRepository extends MongoRepository<DonationResponse, String> {

	List<DonationResponse> findByDonor(DonorProfile donor);

	List<DonationResponse> findByBloodRequest(BloodRequest bloodRequest);

	Optional<DonationResponse> findByBloodRequestAndDonor(BloodRequest bloodRequest, DonorProfile donor);

	boolean existsByBloodRequestAndDonor(BloodRequest bloodRequest, DonorProfile donor);
}