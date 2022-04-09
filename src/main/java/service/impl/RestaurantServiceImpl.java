package service.impl;

import dao.RestaurantRepository;
import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.Restaurant;
import service.RestaurantService;

import java.util.Collection;

public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Collection<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) throws InvalidEntityDataException {

        restaurantRepository.create(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws NonexistingEntityException, InvalidEntityDataException {

        restaurantRepository.update(restaurant);
    }

    @Override
    public void deleteRestaurantById(Long id) throws NonexistingEntityException, InvalidEntityDataException {
//        if (!user.getRole().equals(ADMIN)) {
//            throw new InvalidEntityDataException("Only users with role ADMIN can delete categories");
//        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant findById(Long id) throws NonexistingEntityException {
        return restaurantRepository.findById(id);
    }

//    public void categoryValidation(Restaurant restaurant, User user) throws InvalidEntityDataException {
//        if (!user.getRole().equals(ADMIN)) {
//            throw new InvalidEntityDataException("Only users with role ADMIN can add categories");
//        }
//    }
}
