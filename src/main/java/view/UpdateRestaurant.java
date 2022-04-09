package view;

import model.Recipe;
import model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UpdateRestaurant {
    public String input(Restaurant restaurant) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new restaurant name");
        String newRestaurantName = scan.next();
        while(newRestaurantName.length() == 0) {
            System.out.println("This is not a valid name");
            newRestaurantName = scan.next();
        }
        restaurant.setName(newRestaurantName);
        return restaurant.getName();
    }
}
