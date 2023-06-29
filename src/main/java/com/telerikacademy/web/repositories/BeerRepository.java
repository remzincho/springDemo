package com.telerikacademy.web.repositories;

import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource("classpath:application.properties")
public class BeerRepository implements IBeerRepository {
    private final String dbUrl, dbUsername, dbPassword;
    //private SessionFactory sessionFactory;

    @Autowired
    public BeerRepository(Environment env) {
        dbUrl = env.getProperty("database.url");
        dbUsername = env.getProperty("database.username");
        dbPassword = env.getProperty("database.password");
        //this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAll() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT beer_id, name, abv FROM beers");
        ) {
            return getBeers(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer getById(int id) {
        String query = "SELECT beer_id, name, abv " +
                "FROM beers " +
                "WHERE beer_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            try (
                    ResultSet resultSet = statement.executeQuery()
            ) {
                List<Beer> result = getBeers(resultSet);
                if (result.size() == 0) {
                    throw new EntityNotFoundException("Beer", id);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Beer getByName(String name) {
        String query = "SELECT beer_id, name, abv " +
                "FROM beers " +
                "WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, name);
            try (
                    ResultSet resultSet = statement.executeQuery()
            ) {
                List<Beer> result = getBeers(resultSet);
                if (result.size() == 0) {
                    throw new EntityNotFoundException("Beer", "name", name);
                }
                return result.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void create(Beer beer) {
        String query = "INSERT INTO beers (name, abv) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, beer.getName());
            statement.setDouble(2, beer.getAbv());
            int rowsAffected = statement.executeUpdate();

            Beer newBeer = getByName(beer.getName());
            beer.setId(newBeer.getId());
            if (rowsAffected == 0) {
                throw new SQLException("Creating beer failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Beer beer) {
        String query = "update beers set " +
                "name = ?," +
                "abv = ?" +
                "where beer_id = ?";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, beer.getName());
            statement.setDouble(2, beer.getAbv());
            statement.setInt(3, beer.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Updating beer failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM beers WHERE beer_id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Deleting beer failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int getNextBeerId() {
        return 0;
    }

    private List<Beer> getBeers(ResultSet beersData) throws SQLException {
        List<Beer> beers = new ArrayList<>();
        while (beersData.next()) {
            Beer beer = new Beer(
                    beersData.getInt("beer_id"),
                    beersData.getString("name"),
                    beersData.getDouble("abv")
            );
            beers.add(beer);
        }
        return beers;
    }
}
