package controller;

import exception.NonexistingEntityException;
import model.Recipe;
import model.Restaurant;
import model.User;
import service.*;
import view.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static model.Role.RESTAURANT_OWNER;

public class LoggedRegisteredUserController {

    private Scanner scan = new Scanner(System.in);
    private RestaurantService restaurantService;
    private RecipeService recipeService;
    private UserService userService;
    private FoodDeliveryService foodDeliveryService;
    private ReservationService reservationService;

    public LoggedRegisteredUserController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.foodDeliveryService = foodDeliveryService;
        this.reservationService = reservationService;
    }

    public void init(User user) {
        LoggedUser loggedUser = new LoggedUser("Welcome: " + user.getUsername(), List.of(
                new LoggedUser.Option("Choose restaurant for reservation", () -> {
                    List<Restaurant> restaurants = new ArrayList<>(restaurantService.getAllRestaurants());
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println(i + ". " + restaurants.get(i).getName());
                    }
                    System.out.println("Choose restaurant");
                    int choice = scan.nextInt();
                    while (choice >= restaurants.size()) {
                        choice = scan.nextInt();
                    }
                    System.out.println(restaurants.get(choice));
                    reservationService.create(new ReservationView().input(restaurants.get(choice), user));
                    return "Thank you for choosing " + restaurants.get(choice).getName();
                }),
                new LoggedUser.Option("Choose restaurant for food delivery", () -> {
                    List<Restaurant> restaurants = new ArrayList<>(restaurantService.getAllRestaurants());
                    for (int i = 0; i < restaurants.size(); i++) {
                        System.out.println(i + ". " + restaurants.get(i).getName());
                    }
                    System.out.println("Choose restaurant");
                    int choice = scan.nextInt();
                    while (choice >= restaurants.size()) {
                        choice = scan.nextInt();
                    }

                    Restaurant chosenRestaurant = restaurants.get(choice);
                    System.out.println(chosenRestaurant);
                    List<Recipe> recipes = recipeService.getAllRecipes().
                            stream().
                            filter(r -> r.getRecipeFromRestaurantId().equals(chosenRestaurant.getId())).
                            collect(Collectors.toList());
                    if (recipes.size() == 0) {
                        return "Sorry this restaurant has no recipes";
                    }
                    for (int i = 0; i < recipes.size(); i++) {
                        System.out.println(i + ". " + recipes.get(i));
                    }
                    int chosenRecipe = scan.nextInt();
                    foodDeliveryService.create(new FoodDeliveryView().input(recipes.get(chosenRecipe), user));
                    return "Thank you for choosing " + chosenRestaurant.getName();
                }),
                new LoggedUser.Option("Change role to restaurant owner", () -> {
                    user.setRole(RESTAURANT_OWNER);
                    userService.updateRole(user);

                    return "Your role was updated, to get the new access you need to logout and login";
                }),
                new LoggedUser.Option("Look for your favorite recipe", () -> {
                    Recipe recipe = new FavoriteDishDialog().input();
                    recipeService.getAllRecipes().forEach(System.out::println);
                    List<Recipe> recipesMatching = recipeService.getAllRecipes().stream().
                            filter(r -> r.getName().equals(recipe.getName())).
                            collect(Collectors.toList());
                    if (recipesMatching.size() == 0) {
                        return "We couldn't find anything matching your search";
                    }
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int priceWay = scan.nextInt();
                    if (priceWay == 1) {
                        recipesMatching.sort(Comparator.comparingDouble(Recipe::getPrice).reversed());
                    } else {
                        recipesMatching.sort(Comparator.comparingDouble(Recipe::getPrice));
                    }
                    recipesMatching.forEach(System.out::println);
                    return "Those are all recipes matching your search \n";
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
