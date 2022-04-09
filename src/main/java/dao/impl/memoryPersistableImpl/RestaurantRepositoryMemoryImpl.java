//package dao.impl;
//
//import dao.RestaurantRepository;
//import exception.NonexistingEntityException;
//import model.Restaurant;
//
//import java.util.*;
//
//public class RestaurantRepositoryMemoryImpl implements RestaurantRepository {
//    private Map<Long, Restaurant> restaurants = new HashMap<Long, Restaurant>(); // O(1) find by ID
//
//    @Override
//    public Collection<Restaurant> findAll() {
//        return restaurants.values();
//    }
//
//    //TODO
//    @Override
//    public List<Restaurant> findAllSorted(Comparator<Restaurant> comparator) {
//        return null;
//    }
//
//
//    @Override
//    public Restaurant findById(Long id) {
//        return restaurants.get(id);
//    }
//
//    @Override
//    public Restaurant create(Restaurant restaurant) {
//        restaurants.put(restaurant.getId(), restaurant);
//        return restaurant;
//    }
//
//    @Override
//    public void addAll(Collection<Restaurant> entities) {
//
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Restaurant update(Restaurant restaurant) throws NonexistingEntityException {
//        Restaurant old = findById(restaurant.getId());
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + restaurant.getId() + "' does not exist.");
//        }
//        restaurants.put(restaurant.getId(), restaurant);
//        return restaurant;
//    }
//
//    @Override
//    public Restaurant deleteById(Long id) throws NonexistingEntityException {
//        Restaurant old = restaurants.remove(id);
//        if(old == null) {
//            throw new NonexistingEntityException("User with ID='" + id + "' does not exist.");
//        }
//        return old;
//    }
//
//    @Override
//    public long count() {
//        return restaurants.size();
//    }
//}
