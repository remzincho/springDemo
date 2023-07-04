package com.telerikacademy.web.repositories.contracts;

import com.telerikacademy.web.models.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();

    User getById(int id);
    User getByUsername(String username);
}
