package view;

import model.Recipe;

import java.util.Scanner;

public class UpdateRecipe {
    public Recipe input(Recipe recipe) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write recipe name");
        String newRecipeName = scan.next();
        while(newRecipeName.length() == 0) {
            System.out.println("Invalid recipe name");
            newRecipeName = scan.next();
        }
        System.out.println("Write ingredients using commas (commas input not implemented)");
        String newIngredients = scan.next();
        while(newIngredients.length() == 0) {
            System.out.println("Invalid ingredients");
            newIngredients = scan.next();
        }
        System.out.println("Write the price");
        double newPrice = scan.nextDouble();
        while(newPrice <= 0) {
            System.out.println("Invalid price, must be above 0");
            newPrice = scan.nextDouble();
        }
        System.out.println("What is the whole weight");
        int newGrams = scan.nextInt();
        while(newGrams <= 0) {
            System.out.println("Invalid weight, must be above 0");
            newGrams = scan.nextInt();
        }
        recipe.setName(newRecipeName);
        recipe.setIngredients(newIngredients);
        recipe.setPrice(newPrice);
        recipe.setGrams(newGrams);
        return recipe;
    }
}
