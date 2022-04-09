package dao.impl;

import dao.RestaurantRepository;
import exception.EntityPersistenceException;
import exception.NonexistingEntityException;
import lombok.extern.slf4j.Slf4j;
import model.Restaurant;

import java.sql.*;
import java.util.*;

@Slf4j
public class RestaurantRepositoryJdbc implements RestaurantRepository {

    public static final String SELECT_ALL_RESTAURANTS =
            "select * from `restaurant`;";
    public static final String INSERT_NEW_RESTAURANTS =
            "insert into `restaurant` (`name`, `restaurantOwnerId`) values (?, ?);";
    public static final String UPDATE_RESTAURANT =
            "update `final_project`.`restaurant` SET `name` = ? WHERE (`id` = ?);";
    private Connection connection;

    public RestaurantRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<Restaurant> findAll() {
        try(var stmt = connection.prepareStatement(SELECT_ALL_RESTAURANTS)) {
            // 4. Set params and execute SQL query
            var rs = stmt.executeQuery();
            // 5. Transform ResultSet to Book
            return toRestaurants(rs);
        } catch (SQLException ex) {
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RESTAURANTS, ex);
        }
    }

    // utility methods
    public List<Restaurant> toRestaurants(ResultSet rs) throws SQLException {
        List<Restaurant> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Restaurant(
                    rs.getLong(1),
                    rs.getString("name"),
                    rs.getLong("restaurantOwnerId")
            ));
        }
        return results;
    }

    @Override
    public List<Restaurant> findAllSorted(Comparator<Restaurant> comparator) {
        return null;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        try(var stmt = connection.prepareStatement(INSERT_NEW_RESTAURANTS, Statement.RETURN_GENERATED_KEYS)) {
            // 4. Set params and execute SQL query
            stmt.setString(1, restaurant.getName());
            stmt.setLong(2, restaurant.getRestaurantOwnerId());
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating restaurant failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.setId(generatedKeys.getLong(1));
                    return restaurant;
                }
                else {
                    throw new EntityPersistenceException("Creating restaurant failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_RESTAURANTS, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RESTAURANTS, ex);
        }
    }

    @Override
    public void addAll(Collection<Restaurant> entities) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Restaurant update(Restaurant entity) throws NonexistingEntityException {
        try(var stmt = connection.prepareStatement(UPDATE_RESTAURANT)) {
            stmt.setString(1, entity.getName());
            stmt.setLong(2, entity.getId());
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating restaurant failed, no rows affected.");
            }

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_RESTAURANTS, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RESTAURANTS, ex);
        }
        return entity;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Restaurant deleteById(Long id) throws NonexistingEntityException {
        return null;
    }

    @Override
    public Restaurant findById(Long id) {
        return null;
    }


}
