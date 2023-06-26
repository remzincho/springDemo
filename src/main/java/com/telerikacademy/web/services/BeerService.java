package com.telerikacademy.web.services;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import com.telerikacademy.web.services.contracts.IBeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService implements IBeerService {
    private IBeerRepository repository;

    @Autowired
    public BeerService(IBeerRepository repository) {
        this.repository = repository;
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
    public void create(Beer beer) {
        boolean duplicateExists = true;

        try {
            repository.getByName(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.create(beer);
    }

    @Override
    public void update(Beer beer) {
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
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public int getNextBeerId() {
        return repository.getNextBeerId();
    }
}