package view;

import model.Recipe;
import model.Restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CreateRestaurant implements EntityDialog<Restaurant>{
    @Override
    public Restaurant input() {
        Scanner scan = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();
        System.out.println("Write the restaurant name");
        String restaurantName = scan.next();
        while(restaurantName.length() == 0) {
            System.out.println("Write the restaurant name");
            restaurantName = scan.next();
        }
        restaurant.setName(restaurantName);

        return restaurant;
    }
}
