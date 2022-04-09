package controller;

import model.*;
import service.*;
import view.*;

import java.util.*;
import java.util.stream.Collectors;

import static model.Role.*;



public class HomepageController {
    private RestaurantService restaurantService;
    private RecipeService recipeService;
    private UserService userService;
    private FoodDeliveryService foodDeliveryService;
    private ReservationService reservationService;

    public HomepageController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.foodDeliveryService = foodDeliveryService;
        this.reservationService = reservationService;
    }

    public void init() {
        Scanner scan = new Scanner(System.in);
        userService.loadData();
        Homepage homepage = new Homepage("My first dialogSite", List.of(
                new Homepage.Option("Register", () -> {
                    User user = new NewUserDialog().input();
                    User created = userService.createUser(user);

                    return "User created successfully" + created;
                }),
                new Homepage.Option("Sign in", () -> {
                    User user = new SignInDialog().input();
                    List<User> allname = userService.getAllUsers().stream().
                            filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())).
                            collect(Collectors.toList());
                    if (allname.size() == 0) {
                        return "Wrong password or username";
                    }
                    User currentUser = allname.get(0);
                    if (currentUser != null) {
                        if (currentUser.getRole().equals(REGISTERED_USER)) {
                            LoggedRegisteredUserController loggedRegisteredUserController = new LoggedRegisteredUserController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                            loggedRegisteredUserController.init(currentUser);
                        } else if (currentUser.getRole().equals(RESTAURANT_OWNER)) {
                            LoggedRestaurantOwnerController loggedRestaurantOwnerController = new LoggedRestaurantOwnerController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                            loggedRestaurantOwnerController.init(currentUser);
                        } else if (currentUser.getRole().equals(ADMIN)) {
                            LoggedAdminController loggedAdminController = new LoggedAdminController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                            loggedAdminController.init(currentUser);
                        }
                        return "You logged out successfully";
                    }
                    return "User doesn't exist or the password is wrong";
                }),
                new Homepage.Option("print all restaurants", () -> {
                    Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
                    restaurants.forEach(System.out::println);
                    return "All restaurants in our db: " + restaurants.size();
                }),
                new Homepage.Option("Look for your favorite recipe", () -> {
                    Recipe recipe = new FavoriteDishDialog().input();
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

//                    System.out.println("Would you like to order for home, if you do write 1");
//                    int choice = scan.nextInt();
//                    if(choice == 1) {
//                        System.out.println("Write the recipe you want");
//                        for(int i = 0; i < recipesMatching.size(); i++) {
//                            System.out.println(i + ". " + recipesMatching.get(i));
//                        }
//
//                    }
                    return "If you want to order anything please register \n";
                })
        ));
        homepage.show();
    }
}
