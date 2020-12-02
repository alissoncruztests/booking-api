package com.api.bookings.controller;

import com.api.bookings.api.UserApi;
import com.api.bookings.model.*;
import com.api.bookings.controller.mapper.UserMapper;
import com.api.bookings.model.UserModel;
import com.api.bookings.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static com.api.bookings.controller.mapper.UserMapper.unmarshall;

@CrossOrigin("*")
@RestController
public class UserController extends BaseController implements UserApi {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ApiUserCodeTO> createUser(@Valid @RequestBody ApiUserTO newUser) {
        UserModel model = UserMapper.marshallUserTo(newUser);
        ApiUserCodeTO response = UserMapper.unmarshallApiUserCodeTO(userService.createUser(model));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiUsersResponseTO> getUsers() {
        Collection<UserModel> models = userService.findAll();

        Collection<ApiUserResponseTO> list  =  unmarshall(models);

        ApiUsersResponseTO responseTO = new ApiUsersResponseTO();
        list.stream().forEach(t -> {
            responseTO.addUsersItem(t);
        });

        return ResponseEntity.ok(responseTO);
    }

    @Override
    public ResponseEntity<ApiUserResponseTO> getUser(String id) {
        UserModel model = userService.findById(id);
        ApiUserResponseTO response = UserMapper.unmarshallUserTo(model);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateUser(String id, @Valid ApiUpdateUserTO user) {
        UserModel model = userService.findById(id);
        userService.updateUser(model);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}