package dao.impl;

import dao.FoodDeliveryRepository;
import exception.EntityPersistenceException;
import model.FoodDelivery;

import java.sql.*;

public class FoodDeliveryRepositoryJdbc implements FoodDeliveryRepository {
    public static final String SELECT_ALL_FOOD_DELIVERIES =
            "select * from `fooddelivery`;";
    public static final String INSERT_NEW_FOOD_DELIVERIES =
            "insert into `fooddelivery` (`restaurantId`, `userId`, `recipeId`, `recipe_Id`, `recipe_recipeFromRestaurantId`, `user_id`, `userAddress`, `userPhoneNumber`) values (?, ?, ?, ?, ?, ?, ?, ?);";

    private Connection connection;

    public FoodDeliveryRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(FoodDelivery foodDelivery) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_FOOD_DELIVERIES, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, foodDelivery.getRestaurantId());
            stmt.setLong(2, foodDelivery.getUserId());
            stmt.setLong(3, foodDelivery.getRecipeId());
            stmt.setLong(4, foodDelivery.getRecipe_Id());
            stmt.setLong(5, foodDelivery.getRecipe_recipeFromRestaurantId());
            stmt.setLong(6, foodDelivery.getUser_Id());
            stmt.setString(7, foodDelivery.getUserAddress());
            stmt.setInt(8, foodDelivery.getUserPhoneNumber());
            System.out.println(stmt.toString());
            var affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    foodDelivery.setFoodDeliveryId(generatedKeys.getLong(1));
                } else {
                    throw new EntityPersistenceException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_FOOD_DELIVERIES, ex);
            }
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_FOOD_DELIVERIES, ex);
        }
    }

}
