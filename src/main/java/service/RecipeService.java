package service;

import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.Recipe;
import model.User;

import java.util.List;

public interface RecipeService {
    void addRecipe(Recipe recipe) throws InvalidEntityDataException;
    void updateRecipe(Recipe recipe) throws NonexistingEntityException, InvalidEntityDataException;
    void deleteRecipeById(Long id) throws NonexistingEntityException, InvalidEntityDataException;
    void getRecipeByShortDescription(Recipe recipe);
    Recipe findById(Long id);
    List<Recipe> getAllRecipes();
}
