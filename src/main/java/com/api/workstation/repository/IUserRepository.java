package com.api.workstation.repository;

import com.api.workstation.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserModel, String> {

}