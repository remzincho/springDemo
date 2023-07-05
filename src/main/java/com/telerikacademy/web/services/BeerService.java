package com.telerikacademy.web.services;

import com.telerikacademy.web.controllers.AuthenticationHelper;
import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.exceptions.UnauthorizedOperationException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import com.telerikacademy.web.services.contracts.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
public class BeerService implements IBeerService {
    private IBeerRepository repository;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public BeerService(IBeerRepository repository, AuthenticationHelper authenticationHelper) {
        this.repository = repository;
        this.authenticationHelper = authenticationHelper;
    }

    @Override
    public List<Beer> getAll() {
        return repository.getAll();
    }

    @Override
    public Beer getById(int id) {
        return repository.getById(id);
    }

    @Override
    public Beer getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public void create(Beer beer, @RequestHeader HttpHeaders headers) {
        boolean duplicateExists = true;
        User username = authenticationHelper.tryGetUser(headers);
        try {
            repository.getByName(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        beer.setOwner(username);
        repository.create(beer);
    }

    @Override
    public void update(Beer beer, User user) {
        if (!user.isAdmin() && !beer.getOwner().equals(user)){
            throw new UnauthorizedOperationException("Only admins or owner can modify beer.");
        }
        boolean duplicateExists = true;

        try {
            Beer existingBeer = repository.getByName(beer.getName());
            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.update(beer);
    }

    @Override
    public void delete(int id, User user) {
        Beer beer = repository.getById(id);
        if (!user.isAdmin() && !beer.getOwner().equals(user)){
            throw new UnauthorizedOperationException("Only admins or owner can delete beer.");
        }
        repository.delete(id);
    }
}
