package com.api.bookings.repository;

import com.api.bookings.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserModel, String> {

}