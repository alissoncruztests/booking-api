package com.api.bookings.service.impl;

import com.api.bookings.model.UserModel;
import com.api.bookings.repository.IUserRepository;
import com.api.bookings.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.joda.time.DateTime.now;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public UserModel createUser(UserModel newUser) {
        if (newUser == null){
            throw new RuntimeException();
        }
        newUser.setCreatedDate(now());
        return userRepository.save(newUser);
    }

    @Override
    public UserModel findById(String id) {
        Optional<UserModel> model = userRepository.findById(id);
        verifyIfPersonExists(id);
        return model.get();
    }

    @Override
    public void deleteUser(String id) {
        verifyIfPersonExists(id);
        userRepository.deleteById(id);
    }

    @Override
    public Collection<UserModel> findAll() {
        List<UserModel> users = userRepository.findAll();
        if (users.isEmpty()){
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public UserModel updateUser(UserModel model) {
        model.setUpdatedDate(now());
        return userRepository.save(model);
    }

    private void verifyIfPersonExists(String id){
        if (!userRepository.findById(id).isPresent()){
            throw new RuntimeException();
        }
    }
}