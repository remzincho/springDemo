package com.telerikacademy.web.controllers;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.DTOs.UserDTO;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.services.contracts.IUserService;
import com.telerikacademy.web.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService service;
    private final UserMapper userMapper;

    @Autowired
    public UserController(IUserService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/wish-list")
    public List<Beer> getWishList(@PathVariable int id) {
        return new ArrayList<>(getById(id).getWishList());
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) {
        try {
            return service.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public User create(@Valid @RequestBody UserDTO userDTO) {
        try {
            User user = userMapper.fromDto(userDTO);
            service.create(user);
            return user;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
