package com.telerikacademy.web.repositories;

import com.telerikacademy.web.models.Beer;

import java.util.List;

public interface IBeerRepository {
    List<Beer> getAll();

    Beer getById(int id);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);

    Beer getByName(String name);
}
