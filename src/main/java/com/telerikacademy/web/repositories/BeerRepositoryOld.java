//package com.telerikacademy.web.repositories;
//
//import com.telerikacademy.web.exceptions.EntityNotFoundException;
//import com.telerikacademy.web.models.Beer;
//import com.telerikacademy.web.repositories.contracts.IBeerRepository;
//import com.telerikacademy.web.repositories.contracts.IStyleRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Repository
//public class BeerRepositoryOld implements IBeerRepository {
//    private List<Beer> beers;
//    private final IStyleRepository styleRepository;
//    private static int id = 1;
//
//    public BeerRepositoryOld(IStyleRepository styleRepository) {
//        this.styleRepository = styleRepository;
//        beers = new ArrayList<>();
//        beers.add(new Beer(id++, "Glarus English Ale", 4.6));
//        beers.add(new Beer(id++, "Rhombus Porter", 5.0));
//        beers.add(new Beer(id++, "Ariana", 4.8));
//    }
//
//    @Override
//    public List<Beer> getAll() {
//        return new ArrayList<>(beers);
//    }
//
//    @Override
//    public Beer getById(int id) {
//        return beers.stream()
//                .filter(b -> b.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
//    }
//
//    @Override
//    public void create(Beer beer) {
//        beers.add(beer);
//    }
//
//    @Override
//    public void update(Beer beer) {
//        Beer beerToUpdate = getById(beer.getId());
//
//        beerToUpdate.setName(beer.getName());
//        beerToUpdate.setAbv(beer.getAbv());
//    }
//
//    @Override
//    public void delete(int id) {
//        Beer beerToDelete = getById(id);
//        beers.remove(beerToDelete);
//    }
//
//    @Override
//    public Beer getByName(String name) {
//        return beers.stream()
//                .filter(b -> b.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
//    }
//
//    @Override
//    public int getNextBeerId() {
//        return id++;
//    }
//}
