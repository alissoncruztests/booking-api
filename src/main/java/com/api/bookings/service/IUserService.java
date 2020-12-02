package com.api.bookings.service;

import com.api.bookings.model.UserModel;

import java.util.Collection;

public interface IUserService {
    UserModel createUser(UserModel newUser);

    UserModel findById(String id);

    void deleteUser(String id);

    Collection<UserModel> findAll();

    UserModel updateUser(UserModel model);
}
