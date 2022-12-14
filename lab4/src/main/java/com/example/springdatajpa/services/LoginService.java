package com.example.springdatajpa.services;

import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.repositories.UserRepository;
import com.example.springdatajpa.security.MD5Class;
import com.example.springdatajpa.somepackage.AuthenticationResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import java.security.*;

@Service
public class LoginService {

    private final UserRepository repository;

    private final TokenService tokenService;

    private final InMemoryUserDetailsManager detailsManager;

    public LoginService(UserRepository repository, TokenService tokenService, InMemoryUserDetailsManager detailsManager) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.detailsManager = detailsManager;
    }

    public ResponseEntity<AuthenticationResponseObject> service(String login, String password) {

        password = MD5Class.MD5(password);

        User user = repository.findByLogin(login);

        if (user == null) {
            throw new BadCredentialsException("User with such login is not existing");
        }

        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Wrong password");
        }

        if (!detailsManager.userExists(login)) {
            detailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(login)
                    .password("{noop}" + password)
                    .authorities("read")
                    .build());
        }

        return ResponseEntity.ok(
                new AuthenticationResponseObject(tokenService.generateToken(login))
        );
    }

    private String jsonWrapper(String message) {
        return "{\"message\":\"" + message + "\"}";
    }

}
