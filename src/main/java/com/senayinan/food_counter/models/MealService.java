package com.senayinan.food_counter.models;

import com.senayinan.food_counter.data.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final FoodItemService foodItemService;
    @Autowired
    public MealService(MealRepository mealRepository, FoodItemService foodItemService) {
        this.mealRepository = mealRepository;
        this.foodItemService = foodItemService;
    }
    // Add FoodItems to a specific meal
    public void addFoodItemsToMeal(Integer mealId, List<FoodItem> foodItems) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() ->
                new IllegalArgumentException("Meal with ID " + mealId + " not found"));

        // Add all provided food items to the meal
        meal.getFoodItems().addAll(foodItems);
        mealRepository.save(meal);
    }

    // Add a single FoodItem by query (fetch from USDA API) and add it to a meal
    public void addFoodItemByQuery(Integer mealId, String query, String apiKey) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() ->
                new IllegalArgumentException("Meal with ID " + mealId + " not found"));

        // Fetch food items from the API
        List<FoodItem> fetchedItems = foodItemService.searchFoodItems(query, apiKey);

        if (!fetchedItems.isEmpty()) {
            // Add fetched food items to the meal
            meal.getFoodItems().addAll(fetchedItems);
            mealRepository.save(meal);
        } else {
            System.out.println("No food items found for query: " + query);
        }
    }
}

