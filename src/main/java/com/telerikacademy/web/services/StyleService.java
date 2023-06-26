package com.telerikacademy.web.services;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Style;
import com.telerikacademy.web.repositories.StyleRepository;
import com.telerikacademy.web.repositories.contracts.IStyleRepository;
import com.telerikacademy.web.services.contracts.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleService implements IStyleService {
    private final IStyleRepository repository;

    @Autowired
    public StyleService(StyleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Style> getAll() {
        return repository.getAll();
    }

    @Override
    public Style getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void create(Style style) {
        boolean duplicateExists = true;

        try {
            repository.getByName(style.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Style", "name", style.getName());
        }

        repository.create(style);
    }

    @Override
    public void update(Style style) {
        boolean duplicateExists = true;

        try {
            Style existingBeer = repository.getByName(style.getName());
            if (existingBeer.getId() == style.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Style", "name", style.getName());
        }

        repository.update(style);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
