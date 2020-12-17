package com.api.bookings.service.integration;

import com.api.bookings.model.LoginModel;
import com.api.bookings.model.LoginResponse;

public interface AuthenticationLogin {

    LoginResponse login(LoginModel model);
}
