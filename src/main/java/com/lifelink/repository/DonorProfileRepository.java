package com.lifelink.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lifelink.entity.DonorProfile;
import com.lifelink.entity.User;

@Repository
public interface DonorProfileRepository extends MongoRepository<DonorProfile, String> {

    Optional<DonorProfile> findByUser(User user);

    boolean existsByUser(User user);

}