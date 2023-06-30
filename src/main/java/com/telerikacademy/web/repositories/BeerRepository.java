package com.telerikacademy.web.repositories;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class BeerRepository implements IBeerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("FROM Beer", Beer.class);
            return query.list();
        }
    }

    @Override
    public Beer getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Beer beer = session.get(Beer.class, id);
            if (beer == null) {
                throw new EntityNotFoundException("Beer", id);
            }
            return beer;
        }
    }

    @Override
    public Beer getByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("FROM Beer WHERE name = :name", Beer.class);
            query.setParameter("name", name);
            List<Beer> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("Beer", "name", name);
            }
            return result.get(0);
        }
    }

    @Override
    public void create(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(beer);
        }
    }

    @Override
    public void update(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = getById(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(beerToDelete);
            session.getTransaction().commit();
        }
    }
}
