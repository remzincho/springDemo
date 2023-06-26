package com.telerikacademy.web.repositories;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Style;
import com.telerikacademy.web.repositories.contracts.IStyleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StyleRepository implements IStyleRepository {
    private final List<Style> styles;

    public StyleRepository() {
        styles = new ArrayList<>();
        styles.add(new Style(1, "Dark"));
        styles.add(new Style(2, "Yale"));
        styles.add(new Style(3, "Blonde"));
        styles.add(new Style(4, "Vice"));
    }

    @Override
    public List<Style> getAll() {
        return new ArrayList<>(styles);
    }

    @Override
    public Style getById(int id) {
        return styles.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style", id));
    }

    @Override
    public Style getByName(String name) {
        return styles.stream()
                .filter(s -> s.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style", "name", name));
    }

    @Override
    public void create(Style style) {
        styles.add(style);
    }

    @Override
    public void update(Style style) {
        Style styleToUpdate = getById(style.getId());
        styleToUpdate.setName(style.getName());
    }

    @Override
    public void delete(int id) {
        Style styleToDelete = getById(id);
        styles.remove(styleToDelete);
    }
}
