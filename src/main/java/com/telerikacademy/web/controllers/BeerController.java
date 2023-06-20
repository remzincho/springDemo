package com.telerikacademy.web.controllers;

import com.telerikacademy.web.models.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {
    private final List<Beer> beers;
    private int indexer = 4;

    public BeerController() {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus English Ale", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
        beers.add(new Beer(3, "Ariana", 4.8));
    }

    @GetMapping
    public List<Beer> getAll() {
        return new ArrayList<>(beers);
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        return beers.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Beer with id %d not found.", id)
                ));
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer){
        Beer beerToAdd = new Beer();
        beerToAdd.setId(indexer++);
        beerToAdd.setName(beer.getName());
        beerToAdd.setAbv(beer.getAbv());

        beers.add(beerToAdd);
        return beerToAdd;
    }
}
