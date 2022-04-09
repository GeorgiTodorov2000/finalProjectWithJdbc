package service.impl;

import dao.FoodDeliveryRepository;
import dao.RecipeRepository;
import model.FoodDelivery;
import service.FoodDeliveryService;

public class FoodDeliveryServiceImpl implements FoodDeliveryService {
    private final FoodDeliveryRepository foodDeliveryRepository;

    public FoodDeliveryServiceImpl(FoodDeliveryRepository foodDeliveryRepository) {
        this.foodDeliveryRepository = foodDeliveryRepository;
    }

    @Override
    public void create(FoodDelivery foodDelivery) {
        foodDeliveryRepository.create(foodDelivery);
    }
}
