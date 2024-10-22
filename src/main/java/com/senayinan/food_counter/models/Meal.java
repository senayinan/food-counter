package com.senayinan.food_counter.models;


import jakarta.persistence.Entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Meal extends AbstractEntity{
    @NotNull(message = "Meal type is required")
    private MealType mealType;
    @NotEmpty(message = "At least one food item is required")
    private List<FoodItem> foodItems;

    public Meal(MealType mealType, FoodItem foodItem) {
        this.mealType = mealType;
        this.foodItems = new ArrayList<>();
        this.foodItems.add(foodItem);
    }
    public Meal()   {
        this.foodItems = new ArrayList<>();// This is normally should be empty but
        // initializing the foodItems list in the no-argument constructor ensures that
        // every time a Meal object is created (even if no food items are added at that moment)

        // Initialize the list to prevent null references
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public double getTotalCalories()    {
        double totalCalories = 0;
        for(FoodItem foodItem : foodItems)  {
            totalCalories += foodItem.getCalories();
        }
        return totalCalories;
    }
    public double getTotalCarbs()   {
        double totalCarbs = 0;
        for(FoodItem foodItem : foodItems)  {
            totalCarbs += foodItem.getCarbs();
        }
        return totalCarbs;
    }
    public double getTotalFat() {
        double totalFat = 0;
        for(FoodItem foodItem : foodItems)  {
            totalFat += foodItem.getFats();
        }
        return totalFat;
    }

    @Override
    public String toString() {
        return "Meal{" +
                ", mealType=" + mealType +
                ", foodItems=" + foodItems +
                '}';
    }

}
