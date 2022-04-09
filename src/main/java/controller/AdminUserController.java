package controller;

import model.User;
import service.*;
import view.LoggedUser;
import view.UpdateUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminUserController {
    private RestaurantService restaurantService;
    private RecipeService recipeService;
    private UserService userService;
    private FoodDeliveryService foodDeliveryService;
    private ReservationService reservationService;

    public AdminUserController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
        this.userService = userService;
        this.foodDeliveryService = foodDeliveryService;
        this.reservationService = reservationService;
    }

    public void init(User user) {
        Scanner scan = new Scanner(System.in);
        LoggedUser loggedUser = new LoggedUser("Welcome: " + user.getUsername(), List.of(
                new LoggedUser.Option("Show users", () -> {
                    userService.getAllUsers().forEach(System.out::println);
                    return "This are all users in our db: " + userService.getAllUsers().size();
                }),
                new LoggedUser.Option("Delete user", () -> {

                    List<User> users = new ArrayList<>(userService.getAllUsers());
                    for(int i = 0; i < users.size(); i++) {
                        System.out.println(i + ". " + users.get(i));
                    }
                    int chosenUser = scan.nextInt();
                    System.out.println("Are you sure you want to DELETE your profile, Type one to proceed");
                    int sure = scan.nextInt();
                    if(sure != 1) {
                        return "Phew you almost deleted the wrong user";
                    }
                    userService.deleteUser(users.get(chosenUser));
                    return "User with id: " + chosenUser + " was deleted";
                }),
                new LoggedUser.Option("Update user", () -> {
                    List<User> users = new ArrayList<>(userService.getAllUsers());
                    for(int i = 0; i < users.size(); i++) {
                        System.out.println(i + ". " + users.get(i));
                    }
                    System.out.println("Write the id of the user you want to update");
                    int chosenUser = scan.nextInt();
                    userService.updateUser(new UpdateUser().input(users.get(chosenUser)));
                    return "User was updated";
                })
        ));
        loggedUser.show();
    }
}
