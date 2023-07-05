package com.telerikacademy.web.services;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.repositories.contracts.IUserRepository;
import com.telerikacademy.web.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getById(int id) {
        return repository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public void create(User user) {
        boolean duplicateExists = true;
        try {
            repository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("User", "username", user.getUsername());
        }

        repository.create(user);
    }
}
