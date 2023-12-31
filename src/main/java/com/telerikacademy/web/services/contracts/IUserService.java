package com.telerikacademy.web.services.contracts;

import com.telerikacademy.web.models.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User getById(int id);
    User getByUsername(String username);
    void create(User user);
}
