package controller;

import model.Recipe;
import model.Restaurant;
import model.User;
import service.*;
import view.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LoggedRestaurantOwnerController {
    private Scanner scan = new Scanner(System.in);
    private RestaurantService restaurantService;
    private RecipeService recipeService;
    private UserService userService;
    private FoodDeliveryService foodDeliveryService;
    private ReservationService reservationService;

    public LoggedRestaurantOwnerController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.foodDeliveryService = foodDeliveryService;
        this.reservationService = reservationService;
    }

    public void init(User user) {
        LoggedUser loggedUser = new LoggedUser("Welcome: " + user.getUsername(), List.of(
                new LoggedUser.Option("Show my restaurants", () -> {
                    List<Restaurant> restaurants = restaurantService.getAllRestaurants().
                            stream().
                            filter(r -> r.getRestaurantOwnerId().equals(user.getId()))
                            .collect(Collectors.toList());
                    restaurants.forEach(System.out::println);
                    return "Those are all restaurants that you own";
                }),
                new LoggedUser.Option("Update restaurant name", () -> {
                    List<Restaurant> restaurants = restaurantService.getAllRestaurants().
                            stream().
                            filter(r -> r.getRestaurantOwnerId().equals(user.getId())).
                            collect(Collectors.toList());
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println(i + ". " + restaurants.get(i).getName());
                    }
                    System.out.println("Write the id of the restaurant you want to update");
                    int chooseRestaurantToUpdate = scan.nextInt();
                    while (chooseRestaurantToUpdate >= restaurants.size()) {
                        System.out.println("No such restaurant");
                        chooseRestaurantToUpdate = scan.nextInt();
                    }
                    Restaurant updateRestaurant = restaurants.get(chooseRestaurantToUpdate);
                    while (updateRestaurant == null) {
                        chooseRestaurantToUpdate = scan.nextInt();
                        updateRestaurant = restaurants.get(chooseRestaurantToUpdate);
                    }

                    updateRestaurant.setName(new UpdateRestaurant().input(updateRestaurant));
                    restaurantService.updateRestaurant(updateRestaurant);
                    return "Restaurant new name: " + updateRestaurant.getName();
                }),
                new LoggedUser.Option("Update recipe", () -> {
                    List<Restaurant> restaurants = restaurantService.getAllRestaurants().
                            stream().
                            filter(r -> r.getRestaurantOwnerId().equals(user.getId()))
                            .collect(Collectors.toList());
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println(i + ". " + restaurants.get(i).getName());
                    }
                    System.out.println("choose the restaurant where you want to update recipe");
                    int chooseRestaurantToUpdate = scan.nextInt();
                    Restaurant chosenRestaurant = restaurants.get(chooseRestaurantToUpdate);

                    while (chosenRestaurant == null) {
                        System.out.println("Looks like this restaurant doesn't exist");
                        chooseRestaurantToUpdate = scan.nextInt();
                        chosenRestaurant = restaurants.get(chooseRestaurantToUpdate);
                    }
                    Restaurant updateRestaurantRecipe = chosenRestaurant;

                    List<Recipe> recipes = recipeService.getAllRecipes().
                            stream().
                            filter(r -> r.getRecipeFromRestaurantId().equals(updateRestaurantRecipe.getId())).
                            collect(Collectors.toList());
                    for (int i = 0; i < recipes.size(); i++) {
                        System.out.println(i + ". " + recipes.get(i).getName());
                    }
                    System.out.println("Write the id of the recipe you want to update");
                    int chooseRecipe = scan.nextInt();
                    Recipe updateRecipe = recipes.get(chooseRecipe);
                    while (updateRecipe == null) {
                        System.out.println("Looks like this recipe doesn't exist");
                        chooseRecipe = scan.nextInt();
                        updateRecipe = recipes.get(chooseRecipe);
                    }
                    new UpdateRecipe().input(updateRecipe);
                    recipeService.updateRecipe(updateRecipe);
                    return "Recipe was updated: " + updateRecipe;
                }),
                new LoggedUser.Option("Create recipe", () -> {
                    List<Restaurant> restaurants = restaurantService.getAllRestaurants().
                            stream().
                            filter(r -> r.getRestaurantOwnerId().equals(user.getId())).
                            collect(Collectors.toList());
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println(i + ". " + restaurants.get(i).getName());
                    }
                    System.out.println("choose the restaurant where you want to add a recipe");
                    int chooseRestaurantToUpdate = scan.nextInt();
                    Restaurant chosenRestaurant = restaurants.get(chooseRestaurantToUpdate);

                    while (chosenRestaurant == null) {
                        System.out.println("Looks like this restaurant doesn't exist");
                        chooseRestaurantToUpdate = scan.nextInt();
                        chosenRestaurant = restaurants.get(chooseRestaurantToUpdate);
                    }
                    Restaurant addRecipeToRestaurant = chosenRestaurant;

                    Recipe newRecipe = new RecipeCreation().input(addRecipeToRestaurant.getId());
                    newRecipe.setRecipeFromRestaurantId(chosenRestaurant.getId());
                    recipeService.addRecipe(newRecipe);
                    return "Your recipe was added: " + newRecipe;
                }),
                new LoggedUser.Option("Delete recipe", () -> {
                    recipeService.getAllRecipes().forEach(System.out::println);
                    System.out.println("Which restaurant do you want to delete");
                    Long choice = scan.nextLong();
                    recipeService.deleteRecipeById(choice);
                    return "";
                }),
                new LoggedUser.Option("Create restaurant", () -> {
                    Restaurant restaurant = new CreateRestaurant().input();
                    restaurant.setRestaurantOwnerId(user.getId());
                    restaurantService.addRestaurant(restaurant);
                    return "Your restaurant was added " + restaurant;
                }),
                new LoggedUser.Option("Delete restaurant", () -> {
                    restaurantService.getAllRestaurants().forEach(System.out::println);
                    System.out.println("Which restaurant do you want to delete");
                    Long choice = scan.nextLong();
                    restaurantService.deleteRestaurantById(choice);
                    return "";
                }),
                new LoggedUser.Option("Update user information", () -> {
                    // We are calling the updateUser from userService
                    // then we call update user from loggedUserController
                    userService.updateUser(updateUser(user));
                    return "Your information got updated, please logout and login to see the updated information " + user;
                }),
                new LoggedUser.Option("Delete my profile", () -> {
                    System.out.println("Are you sure you want to DELETE your profile");
                    int choice = scan.nextInt();
                    if (choice == 1) {
                        System.out.println("We are sorry to hear you are leaving us");
                        userService.deleteUser(user);
                    } else {
                        return "Phew we almost lost you, thanks for deciding to stay with us";
                    }
                    return "Your profile was deleted, please email us on" +
                            " fakeEmail@abv to tell us why you decided to delete your profile ";
                })

        ));
        loggedUser.show();
    }

    public User updateUser(User user) {
        return new UpdateUser().input(user);
    }

}
