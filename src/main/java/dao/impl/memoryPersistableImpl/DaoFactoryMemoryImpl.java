//package dao.impl;
//
//import dao.*;
//
//public class DaoFactoryMemoryImpl implements DaoFactory {
//
//    @Override
//    public UserRepository createUserRepository(String userDbFilename) {
//
//        return new UserRepositoryMemoryImpl();
//    }
//
//    @Override
//    public RecipeRepository createRecipeRepository() { return new RecipeRepositoryMemoryImpl(); }
//
//    @Override
//    public RestaurantRepository createRestaurantRepository() { return new RestaurantRepositoryMemoryImpl(); }
//}