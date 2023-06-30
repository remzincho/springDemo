package com.telerikacademy.web.repositories;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Style;
import com.telerikacademy.web.repositories.contracts.IStyleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StyleRepository implements IStyleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public StyleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //    @Override
//    public List<Style> getAll() {
//        return new ArrayList<>(styles);
//    }
//
    @Override
    public Style getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);
            if (style == null) {
                throw new EntityNotFoundException("Style", id);
            }
            return style;
        }
    }
//
//    @Override
//    public Style getByName(String name) {
//        return styles.stream()
//                .filter(s -> s.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("Style", "name", name));
//    }
//
//    @Override
//    public void create(Style style) {
//        styles.add(style);
//    }
//
//    @Override
//    public void update(Style style) {
//        Style styleToUpdate = getById(style.getId());
//        styleToUpdate.setName(style.getName());
//    }
//
//    @Override
//    public void delete(int id) {
//        Style styleToDelete = getById(id);
//        styles.remove(styleToDelete);
//    }
}
