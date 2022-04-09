//package controller;
//
//import model.*;
//import service.*;
//import view.*;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Scanner;
//import java.util.stream.Collectors;
//
//import static model.Role.*;
//
//public class LoggedUserController {
//    private Scanner scan = new Scanner(System.in);
//    private RestaurantService restaurantService;
//    private RecipeService recipeService;
//    private UserService userService;
//    private FoodDeliveryService foodDeliveryService;
//    private ReservationService reservationService;
//
//    public LoggedUserController(RestaurantService restaurantService, RecipeService recipeService, UserService userService, FoodDeliveryService foodDeliveryService, ReservationService reservationService) {
//        this.restaurantService = restaurantService;
//        this.recipeService = recipeService;
//        this.userService = userService;
//        this.foodDeliveryService = foodDeliveryService;
//        this.reservationService = reservationService;
//    }
//
//    public void init(User user) {
//        if (user.getRole().equals(REGISTERED_USER)) {
//
//        } else if (user.getRole().equals(RESTAURANT_OWNER)) {
//
//        } else if (user.getRole().equals(ADMIN)) {
//            LoggedUser loggedUser = new LoggedUser("Welcome: " + user.getUsername(), List.of(
//                    new LoggedUser.Option("Show all restaurants", () -> {
//                        restaurantService.getAllRestaurants().
//                                forEach(System.out::println);
//                        return "Those are all restaurants that you own";
//                    }),
//                    new LoggedUser.Option("Create restaurant", () -> {
//                        Restaurant restaurant = new CreateRestaurant().input();
//                        restaurant.setRestaurantOwnerId(user.getId());
//                        restaurantService.addRestaurant(restaurant);
//                        return "Your restaurant was added " + restaurant;
//                    })
//            ));
//            loggedUser.show();
//        }
//    }
//
//    public User updateUser(User user) {
//        return new UpdateUser().input(user);
//    }
//}
