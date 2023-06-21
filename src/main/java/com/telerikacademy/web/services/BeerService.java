package com.telerikacademy.web.services;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BeerService {
    private BeerRepository repository;
    private int indexer = 4;

    @Autowired
    public BeerService(BeerRepository repository) {
        this.repository = repository;
    }

    public List<Beer> getAll() {
        return repository.getAll();
    }

    public Beer getById(int id){
        return repository.getById(id);
    }

    public void create(Beer beer){
       boolean duplicateExists = true;

       try{
           repository.getByName(beer.getName());
       }catch (EntityNotFoundException e){
           duplicateExists=false;
       }

       if (duplicateExists){
           throw new DuplicateEntityException("Beer","name",beer.getName());
       }

        repository.create(beer);
    }

    public void update(Beer beer) {
        boolean duplicateExists = true;

        try{
            Beer existingBeer = repository.getByName(beer.getName());
            if (existingBeer.getId() == beer.getId()){
                duplicateExists=false;
            }
        }catch(EntityNotFoundException e){
            duplicateExists=false;
        }

        if (duplicateExists){
            throw new DuplicateEntityException("Beer","name", beer.getName());
        }

        repository.update(beer);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
