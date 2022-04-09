package service;

import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.Restaurant;

import java.util.Collection;

public interface RestaurantService {
    void addRestaurant(Restaurant restaurant) throws InvalidEntityDataException;
    void updateRestaurant(Restaurant restaurant) throws NonexistingEntityException, InvalidEntityDataException;
    void deleteRestaurantById(Long id) throws NonexistingEntityException, InvalidEntityDataException;
    Collection<Restaurant> getAllRestaurants();

    Restaurant findById(Long id) throws NonexistingEntityException;
}
