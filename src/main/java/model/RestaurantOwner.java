package model;

import java.util.Collection;
import java.util.List;

public class RestaurantOwner extends User{
    private Collection<Restaurant> restaurants;

    public RestaurantOwner(String firstName, String lastName, String email, String username, String password, Gender gender, Role role, StatusReader status, Collection<Restaurant> restaurants) {
        super(firstName, lastName, email, username, password, gender, role, status);
        this.restaurants = restaurants;
    }

    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public String toString() {
        return "RestaurantOwner{" +
                " restaurants= " + restaurants +
                "} ";
    }
}
