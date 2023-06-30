package com.telerikacademy.web.repositories.contracts;

import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.Style;

import java.util.List;

public interface IStyleRepository {
    //    List<Style> getAll();
    Style getById(int id);
//    Style getByName(String name);
//
//    void create(Style style);
//
//    void update(Style style);
//
//    void delete(int id);
}
