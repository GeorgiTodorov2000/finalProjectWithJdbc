package model;

import dao.Identifiable;

public class Recipe implements Identifiable<Long> {
    private Long id;
    private String name;
    private String ingredients;
    private double price;
    private int grams;
    private Long recipeFromRestaurantId;

    public Recipe() {
    }

    public Recipe(Long id, String name, String ingredients, double price, int grams, Long recipeFromRestaurantId) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.grams = grams;
        this.recipeFromRestaurantId = recipeFromRestaurantId;
    }

    public Recipe(String name, String ingredients, double price, int grams, Long recipeFromRestaurantId) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.grams = grams;
        this.recipeFromRestaurantId = recipeFromRestaurantId;
    }

    public Long getRecipeFromRestaurantId() {
        return recipeFromRestaurantId;
    }

    public void setRecipeFromRestaurantId(Long recipeFromRestaurantId) {
        this.recipeFromRestaurantId = recipeFromRestaurantId;
    }

    public Recipe(String dishName) {
        this.name = dishName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                " |  name= " + name +
                " | ingredients= " + ingredients +
                " | price= " + price +
                " | grams= " + grams +
                " | recipeFromRestaurantId= " + recipeFromRestaurantId +
                '}';
    }

}
