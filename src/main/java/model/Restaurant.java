package model;

import dao.Identifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Restaurant implements Identifiable<Long> {
    private Long id;
    private String name;
    private Long restaurantOwnerId;
    public static List<Recipe> RECIPES = new ArrayList<Recipe>();


    public Restaurant() {
    }

    public Restaurant(Long id, String name, Long restaurantOwnerId) {
        this.id = id;
        this.name = name;
        this.restaurantOwnerId = restaurantOwnerId;
    }

    public Restaurant(String name, Long restaurantOwnerId) {
        this.name = name;
        this.restaurantOwnerId = restaurantOwnerId;
    }

    public Long getRestaurantOwnerId() {
        return restaurantOwnerId;
    }

    public void setRestaurantOwnerId(Long restaurantOwnerId) {
        this.restaurantOwnerId = restaurantOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
