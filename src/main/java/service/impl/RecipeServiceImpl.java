package service.impl;

import dao.RecipeRepository;
import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.Recipe;
import model.User;
import service.RecipeService;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }


    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void addRecipe(Recipe recipe) throws InvalidEntityDataException {
        recipeValidation(recipe);
        recipeRepository.create(recipe);
    }

    @Override
    public void updateRecipe(Recipe recipe) throws NonexistingEntityException, InvalidEntityDataException {
        recipeValidation(recipe);
        recipeRepository.update(recipe);
    }

    @Override
    public void deleteRecipeById(Long id) throws NonexistingEntityException, InvalidEntityDataException {
        recipeRepository.deleteById(id);
    }



    @Override
    public void getRecipeByShortDescription(Recipe recipe) {

    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id);
    }

    public void recipeValidation(Recipe recipe) throws InvalidEntityDataException {

    }
}
