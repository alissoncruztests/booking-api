package com.api.bookings.controller;

import com.api.bookings.model.ApiBookingResponseTO;
import com.api.bookings.model.LoginModel;
import com.api.bookings.model.LoginResponse;
import com.api.bookings.service.integration.AuthenticationLogin;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController extends BaseController{

    @Autowired
    private AuthenticationLogin authenticationLogin;

    @RequestMapping(value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<LoginResponse>  login(@ApiParam(value = "Login object" ,required=true ) @RequestBody LoginModel model){
        LoginResponse response = authenticationLogin.login(model);

        return ResponseEntity.ok(response);
    }
}
