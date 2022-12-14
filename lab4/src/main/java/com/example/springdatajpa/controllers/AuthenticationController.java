package com.example.springdatajpa.controllers;

import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.services.LoginService;
import com.example.springdatajpa.services.RegistrationService;
import com.example.springdatajpa.somepackage.AuthenticationResponseObject;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final RegistrationService registrationService;

    private final LoginService loginService;

    public AuthenticationController(RegistrationService registrationService, LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseObject> login(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        return loginService.service(user.getLogin(), user.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return registrationService.service(user.getLogin(), user.getPassword());
    }
}
