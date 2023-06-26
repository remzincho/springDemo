package com.telerikacademy.web.services.contracts;

import com.telerikacademy.web.models.Style;

import java.util.List;

public interface IStyleService {
    List<Style> getAll();
    Style getById(int id);
    void create(Style style);

    void update(Style style);

    void delete(int id);
}
