package dao;

import model.Recipe;

public interface RecipeRepository extends Repository<Long, Recipe> {

    Recipe findById(Long id);

    Recipe findById(long id);
}