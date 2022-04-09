package controller;

import model.User;
import service.*;
import view.LoggedUser;
import view.UpdateUser;

import java.util.List;
import java.util.Scanner;

public class LoggedAdminController {
    private Scanner scan = new Scanner(System.in);
    private RestaurantService restaurantService;
    private RecipeService recipeService;
    private UserService userService;
    private FoodDeliveryService foodDeliveryService;
    private ReservationService reservationService;

    public LoggedAdminController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.foodDeliveryService = foodDeliveryService;
        this.reservationService = reservationService;
    }

    public void init(User user) {
        LoggedUser loggedUser = new LoggedUser("Welcome: " + user.getUsername(), List.of(
                new LoggedUser.Option("Show user menu", () -> {
                    AdminUserController adminUserController = new AdminUserController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                    adminUserController.init(user);
                    return "This was user menu";
                }),
                new LoggedUser.Option("Show register user menu", () -> {
                    LoggedRegisteredUserController loggedRegisteredUserController = new LoggedRegisteredUserController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                    loggedRegisteredUserController.init(user);
                    return "This was registered user menu";
                }),
                new LoggedUser.Option("Show restaurant owner menu", () -> {
                    LoggedRestaurantOwnerController loggedRestaurantOwnerController = new LoggedRestaurantOwnerController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
                    loggedRestaurantOwnerController.init(user);
                    return "This was restaurant owner menu";
                })
        ));
        loggedUser.show();
    }

    public User updateUser(User user) {
        return new UpdateUser().input(user);
    }
}
