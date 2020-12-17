package com.api.bookings.service.integration.impl;

import com.api.bookings.model.LoginModel;
import com.api.bookings.model.LoginResponse;
import com.api.bookings.service.integration.AuthenticationLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthenticationLoginImp implements AuthenticationLogin {

    @Autowired
    private WebClient loginWebClient;

    @Override
    public LoginResponse login(LoginModel model) {
        Mono<HttpHeaders> result = loginWebClient.post()
                .uri("/login")
                .body(BodyInserters.fromValue(LoginModel.builder()
                        .username(model.getUsername())
                        .password(model.getPassword())
                        .build()))
                .exchange()
                .map(response -> response.headers().asHttpHeaders());

        HttpHeaders headers = result.block();
        String jwt = headers.get("Authorization").get(0);

        LoginResponse response  = LoginResponse.builder()
                .token(jwt)
                .build();
        System.out.println( result.block().toString());

        /*Mono<LoginModel> monoLogin =
                this.loginWebClient
                        .post()
                        .uri("/login")
                        .body(BodyInserters.fromValue(LoginModel.builder()
                                .username(model.getUsername())
                                .password(model.getPassword())
                                .build()))
                        .retrieve()
                        .bodyToMono(LoginModel.class);

*/
        return response;
    }


}
