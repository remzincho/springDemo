package com.telerikacademy.web.controllers;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private final UserService service;

    @Autowired
    public AuthenticationHelper(UserService service) {
        this.service = service;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER_NAME)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "The request resource requires authentication.");
        }

        try {
            String username = headers.getFirst(AUTHORIZATION_HEADER_NAME);
            return service.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username");
        }
    }
}
