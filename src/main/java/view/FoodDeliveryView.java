package view;

import model.FoodDelivery;
import model.Recipe;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodDeliveryView {
    public FoodDelivery input(Recipe recipe, User user) {
        Scanner scan = new Scanner(System.in);
        FoodDelivery foodDelivery = new FoodDelivery();

        System.out.println("Write your address");
        String address = scan.next();
        while (address.length() == 0) {
            System.out.println("Invalid address");
            address = scan.next();
        }
        System.out.println("Write your phone number");
        int phoneNumber = scan.nextInt();
        // there is no phone number smaller than 6 numbers
        while(phoneNumber < 100000) {
            phoneNumber = scan.nextInt();
        }
        System.out.println("The food will be delivered to: " + address + " for the price of " + recipe.getPrice()
                + " The restaurant may contact you on: " + phoneNumber);
        foodDelivery.setRestaurantId(recipe.getRecipeFromRestaurantId());
        foodDelivery.setUserId(user.getId());
        foodDelivery.setRecipeId(recipe.getId());
        foodDelivery.setRecipe_Id(recipe.getId());
        foodDelivery.setRecipe_recipeFromRestaurantId(recipe.getRecipeFromRestaurantId());
        foodDelivery.setUser_Id(user.getId());
        foodDelivery.setUserAddress(address);
        foodDelivery.setUserPhoneNumber(phoneNumber);
        return foodDelivery;
    }
}

