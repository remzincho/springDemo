package com.telerikacademy.web.services.contracts;

import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.User;

import java.util.List;

public interface IBeerService {
    List<Beer> getAll();

    Beer getById(int id);

    Beer getByName(String name);

    void create(Beer beer);

    void update(Beer beer, User user);

    void delete(int id, User user);
}
