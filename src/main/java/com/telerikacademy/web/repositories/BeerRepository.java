package com.telerikacademy.web.repositories;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerRepository {
    private List<Beer> beers;

    @Autowired
    public BeerRepository() {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus English Ale", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
        beers.add(new Beer(3, "Ariana", 4.8));
    }

    public List<Beer> getAll() {
        return new ArrayList<>(beers);
    }

    public Beer getById(int id) {
        return beers.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    public void create(Beer beer) {
        beers.add(beer);
    }

    public void update(Beer beer) {
        Beer beerToUpdate = getById(beer.getId());

        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    public void delete(int id) {
        Beer beerToDelete = getById(id);
        beers.remove(beerToDelete);
    }

    public Beer getByName(String name) {
        return beers.stream()
                .filter(b -> b.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }
}
