package com.senayinan.food_counter.models;

import com.senayinan.food_counter.data.MealRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<Meal> getMealsByDate(LocalDate date) {
        return mealRepository.findByDate(date);  // Query meals by date from the repository
    }

    // Method to get all meals
    public List<Meal> getAllMeals() {
        Iterable<Meal> mealsIterable = mealRepository.findAll();
        // Convert Iterable to List
        return convertIterableToList(mealsIterable);
    }

    // Helper method to convert Iterable to List
    private List<Meal> convertIterableToList(Iterable<Meal> iterable) {
        List<Meal> mealList = new ArrayList<>();
        iterable.forEach(mealList::add);  // Add each item to the list
        return mealList;
    }


    // Add FoodItems to a specific meal
    @Transactional
    public void addFoodItemsToMeal(Long mealId, List<FoodItem> foodItems) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() ->
                new IllegalArgumentException("Meal with ID " + mealId + " not found"));

        // Add all provided food items to the meal
        meal.getFoodItems().addAll(foodItems);
        mealRepository.save(meal);
    }

    // Add a single FoodItem by query (fetch from USDA API) and add it to a meal
    @Transactional
    public void addFoodItemByQuery(Long mealId, String query, String apiKey) {
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


    public void saveMeal(Meal newMeal) {
        if (newMeal == null) {
            throw new IllegalArgumentException("The meal cannot be null.");
        }

        // Ensure the meal has at least one food item
        if (newMeal.getFoodItems() == null || newMeal.getFoodItems().isEmpty()) {
            throw new IllegalArgumentException("Meal must contain at least one food item.");
        }

        // Ensure the date is set, default to the current date if not provided
        if (newMeal.getDate() == null) {
            newMeal.setDate(LocalDate.now());
        }

        // Save the meal
        mealRepository.save(newMeal);
        System.out.println("Meal saved successfully: " + newMeal);
    }


/*the last version before the updates

    public void saveMeal(Meal newMeal) {
        try {
            // Check if the meal object is valid
            if (newMeal == null) {
                throw new IllegalArgumentException("The meal cannot be null.");
            }

            // Ensure the meal has food items if necessary
            if (newMeal.getFoodItems() == null) {
                newMeal.setFoodItems(new ArrayList<>());  // Initialize the list if it's null
            }

            if (newMeal.getFoodItems().isEmpty()) {
                throw new IllegalArgumentException("Meal must contain at least one food item.");
            }

            // Ensure the date is set for the meal
            if (newMeal.getDate() == null) {
                throw new IllegalArgumentException("Meal date cannot be null.");
            }

            // Handle food items - Make sure food items are properly associated
            for (FoodItem foodItem : newMeal.getFoodItems()) {
                if (foodItem.getName() == null || foodItem.getName().isEmpty()) {
                    throw new IllegalArgumentException("Food item must have a name.");
                }
                // Add additional validation if needed for food items
            }

            // Check if the meal is new or existing based on ID
            if (newMeal.getId() == null) {
                System.out.println("Creating a new meal.");
            } else {
                System.out.println("Updating an existing meal with ID: " + newMeal.getId());
            }

            // Save the meal object using the repository
            mealRepository.save(newMeal);
            System.out.println("Meal saved successfully: " + newMeal);

        } catch (IllegalArgumentException e) {
            System.out.println("Validation error while saving meal: " + e.getMessage());
        } catch (Exception e) {
            // Log the error with detailed information
            System.out.println("Error saving meal: " + e.getMessage());
            e.printStackTrace(); // For full stack trace in case of error, remove this in production code
        }
    }

 */





    /*
    // Method to save the meal
    public void saveMeal(Meal newMeal) {
        mealRepository.save(newMeal);  // Save the meal object using the repository
    }

     */

    // Method to delete meal by ID
    public void deleteMealById(Long mealId) {
        mealRepository.deleteById(mealId);  // Delete meal by ID using repository
    }

    // Method to retrieve a meal by its ID
    public Optional<Meal> getMealById(Long mealId) {
        return mealRepository.findById(mealId);
    }


}

