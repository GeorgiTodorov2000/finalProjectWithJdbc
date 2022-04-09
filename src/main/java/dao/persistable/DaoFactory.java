package dao.persistable;

import dao.RecipeRepository;
import dao.RestaurantRepository;
import dao.UserRepository;

public interface DaoFactory {
    UserRepository createUserRepository(String userDbFilename);
    RecipeRepository createRecipeRepository();
    RestaurantRepository createRestaurantRepository();
}
