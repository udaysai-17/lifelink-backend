package com.lifelink.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lifelink.entity.RequesterProfile;
import com.lifelink.entity.User;

@Repository
public interface RequesterProfileRepository extends MongoRepository<RequesterProfile, String> {

    Optional<RequesterProfile> findByUser(User user);

    boolean existsByUser(User user);
}