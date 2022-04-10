package dao.impl;

import dao.RecipeRepository;
import exception.EntityPersistenceException;
import exception.NonexistingEntityException;
import lombok.extern.slf4j.Slf4j;
import model.Recipe;

import java.sql.*;
import java.util.*;

@Slf4j
public class RecipeRepositoryJdbc implements RecipeRepository {

    public static final String SELECT_ALL_RECIPES =
            "select * from `recipe`;";
    public static final String INSERT_NEW_RECIPE =
            "insert into `recipe` (`name`, `ingredients`, `price`, `grams`, `recipeFromRestaurantId`) values (?, ?, ?, ?, ?);";
    public static final String UPDATE_RECIPE =
            "UPDATE `recipe` SET `name` = ?, `ingredients` = ?, `price` = ?, `grams` = ? WHERE (`id` = ?);";

    private Connection connection;

    public RecipeRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Collection<Recipe> findAll() {
        try (var stmt = connection.prepareStatement(SELECT_ALL_RECIPES)) {
            // 4. Set params and execute SQL query
            var rs = stmt.executeQuery();
            // 5. Transform ResultSet to Book
            return toRecipe(rs);
        } catch (SQLException ex) {
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RECIPES, ex);
        }
    }

    // utility methods
    public List<Recipe> toRecipe(ResultSet rs) throws SQLException {
        List<Recipe> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new Recipe(
                    rs.getLong(1),
                    rs.getString("name"),
                    rs.getString("ingredients"),
                    rs.getDouble("price"),
                    rs.getInt("grams"),
                    rs.getLong("recipeFromRestaurantId")
            ));
        }
        return results;
    }

    @Override
    public List<Recipe> findAllSorted(Comparator<Recipe> comparator) {
        return null;
    }

    @Override
    public Recipe create(Recipe recipe) {
        try (var stmt = connection.prepareStatement(INSERT_NEW_RECIPE, Statement.RETURN_GENERATED_KEYS)) {
            // 4. Set params and execute SQL query
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getIngredients());
            stmt.setDouble(3, recipe.getPrice());
            stmt.setInt(4, recipe.getGrams());
            stmt.setLong(5, recipe.getRecipeFromRestaurantId());

            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating recipe failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    recipe.setId(generatedKeys.getLong(1));
                    return recipe;
                } else {
                    throw new EntityPersistenceException("Creating recipe failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_RECIPES, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RECIPES, ex);
        }
    }

    @Override
    public void addAll(Collection<Recipe> entities) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Recipe update(Recipe recipe) throws NonexistingEntityException {
        try (var stmt = connection.prepareStatement(UPDATE_RECIPE)) {
            // 4. Set params and execute SQL query
            stmt.setString(1, recipe.getName());
            stmt.setString(2, recipe.getIngredients());
            stmt.setDouble(3, recipe.getPrice());
            stmt.setInt(4, recipe.getGrams());
            stmt.setLong(5, recipe.getId());
            // 5. Execute insert statement
            connection.setAutoCommit(false);
            var affectedRows = stmt.executeUpdate();
            // more updates here ...
            connection.commit();
            connection.setAutoCommit(true);

            // 6. Check results and Get generated primary key
            if (affectedRows == 0) {
                throw new EntityPersistenceException("Creating recipe failed, no rows affected.");
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new EntityPersistenceException("Error rolling back SQL query: " + SELECT_ALL_RECIPES, ex);
            }
            log.error("Error creating connection to DB", ex);
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_RECIPES, ex);
        }
        return recipe;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) throws NonexistingEntityException {
    }

    @Override
    public Recipe findById(Long id) {
        return null;
    }

    @Override
    public Recipe findById(long id) {
        return null;
    }


}
