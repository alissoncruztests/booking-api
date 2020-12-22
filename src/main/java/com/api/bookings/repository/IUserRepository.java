package com.api.bookings.repository;

import com.api.bookings.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}