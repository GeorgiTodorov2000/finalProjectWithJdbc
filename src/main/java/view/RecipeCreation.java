package view;

import model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RecipeCreation {
    public Recipe input(Long restaurantId) {
        Recipe recipe = new Recipe();
        Scanner scan = new Scanner(System.in);
        System.out.println("Write recipe name");
        String recipeName = scan.next();
        while(recipeName.length() == 0) {
            System.out.println("Invalid name");
            recipeName = scan.next();
        }
        System.out.println("Write ingredients using commas (commas input not implemented)");
        String ingredients = scan.next();
        while(ingredients.length() == 0) {
            System.out.println("Invalid name");
            ingredients = scan.next();
        }
        System.out.println("Write the price");
        double price = scan.nextDouble();
        while(price <= 0) {
            System.out.println("Invalid price, must be above 0");
            price = scan.nextDouble();
        }
        System.out.println("What is the whole weight");
        int grams = scan.nextInt();
        while(grams <= 0) {
            System.out.println("Invalid weight, must be above 0");
            grams = scan.nextInt();
        }
        recipe.setName(recipeName);
        recipe.setIngredients(ingredients);
        recipe.setPrice(price);
        recipe.setGrams(grams);
        return recipe;
    }
}
