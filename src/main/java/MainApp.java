import controller.HomepageController;
import dao.*;
import dao.impl.*;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.*;
import service.*;
import service.impl.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static model.Gender.FEMALE;
import static model.Gender.MALE;
import static model.Role.*;
import static model.StatusReader.ACTIVE;
import static util.JdbcUtils.closeConnection;
import static util.JdbcUtils.createDbConnection;

public class MainApp {
    public static List<Recipe> RECIPES = new ArrayList<Recipe>();
    public static List<Restaurant> RESTAURANTS = new ArrayList<Restaurant>();

    private static final Scanner scan = new Scanner(System.in);


//    private static final RecipeRepository recipeRepository = daoFactory.createRecipeRepository();
//    private static final RestaurantRepository restaurantRepository= daoFactory.createRestaurantRepository();
//
//    private static final RecipeService recipeService = new RecipeServiceImpl(recipeRepository);
//    private static final RestaurantService restaurantService = new RestaurantServiceImpl(restaurantRepository);

    public static final String USER_DB_FILENAME = "user1.db";

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        UserRepository userRepositoryFile = daoFactory.createUserRepository(USER_DB_FILENAME);
//        UserService userService = new UserServiceImpl(userRepositoryFile);

        // Read app properties from file
        Properties props = new Properties();
        String dbConfigPath = MainApp.class.getClassLoader()
                .getResource("jdbc.properties").getPath();
        props.load(new FileInputStream(dbConfigPath));

        // Create DB Connection
        Connection conn = createDbConnection(props);
        UserRepository userRepository = new UserRepositoryJdbc(conn);
        UserService userService = new UserServiceImpl(userRepository);

        RecipeRepository recipeRepository = new RecipeRepositoryJdbc(conn);
        RecipeService recipeService = new RecipeServiceImpl(recipeRepository);

        RestaurantRepository restaurantRepository = new RestaurantRepositoryJdbc(conn);
        RestaurantService restaurantService = new RestaurantServiceImpl(restaurantRepository);

        FoodDeliveryRepository foodDeliveryRepository = new FoodDeliveryRepositoryJdbc(conn);
        FoodDeliveryService foodDeliveryService = new FoodDeliveryServiceImpl(foodDeliveryRepository);

        ReservationRepository reservationRepository = new ReservationRepositoryJdbc(conn);
        ReservationService reservationService = new ReservationServiceImpl(reservationRepository);

//        Collection<User> users = userService.getAllUsers();
//        users.forEach(System.out::println);
//        Collection<Recipe> recipes = recipeService.getAllRecipes();
//        recipes.forEach(System.out::println);
//        Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
//        restaurants.forEach(System.out::println);

        /** if you want to use admin role
//        User user = new User("Admin", "Admin", "admin@abv", "Admin", "123321", MALE, ADMIN, ACTIVE);
//        try {
//            userService.createUser(user);
//        } catch (InvalidEntityDataException e) {
//            e.printStackTrace();
//        }
        **/
        HomepageController homepageController = new HomepageController(restaurantService, recipeService, userService, foodDeliveryService, reservationService);
        homepageController.init();

        // Close DB connection
        closeConnection(conn);

    }
}

