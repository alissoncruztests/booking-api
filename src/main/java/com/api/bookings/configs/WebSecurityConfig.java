package com.api.bookings.configs;


import com.api.bookings.security.JWTAuthenticationFilter;
import com.api.bookings.security.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/booking/v1/login").permitAll()
                    .antMatchers("/booking/v1/booking").permitAll()
                    .antMatchers("/booking/v1/loginByEmail").permitAll()
                    .antMatchers("/booking/v1/user").permitAll()
                    .antMatchers("/booking/v1/loginByEmail").permitAll()
                    .antMatchers("/booking/v1/place").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                //filtra requisições de login
                .addFilterBefore(new JWTLoginFilter("/booking/v1/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //filtra as demais requisições
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // cria uma conta default
        auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password("password")
                    .roles("ADMIN", "USER", "WHATEVER")
                .and()
                    .withUser("user")
                    .password("password")
                    .roles("USER");
    }
}
