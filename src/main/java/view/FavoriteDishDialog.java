package view;

import model.Recipe;

import java.util.Scanner;

public class FavoriteDishDialog implements EntityDialog<Recipe>{

    @Override
    public Recipe input() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your favorite dish name");
        String dishName = scan.next();
        while (dishName.length() == 0) {
            System.out.println("Invalid dish name");
            dishName = scan.next();
        }
        Recipe recipe = new Recipe(dishName);
        return recipe;
    }
}
