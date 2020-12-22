package com.api.bookings.controller;

import com.api.bookings.model.*;
import com.api.bookings.service.impl.UserService;
import com.api.bookings.service.integration.AuthenticationLogin;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static com.api.bookings.security.TokenAuthenticationService.addAuthenticationString;

@RestController
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/loginByEmail",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<LoginByEmailModel>  loginByEmail(@ApiParam(value = "Login object" ,required=true ) @RequestBody LoginByEmailModel model){
        UserModel user = userService.findByEmail(model.getEmail());
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        List<String> authorities = new ArrayList<>();
        authorities.add("USER");
        String jwt = "token";
        LoginByEmailModel response = LoginByEmailModel.builder()
                .email(user.getEmail())
                .jwt(jwt)
                .build();

        return ResponseEntity.ok(response);
    }
}
