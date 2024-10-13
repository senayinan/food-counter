package com.senayinan.food_counter.models;


import java.util.ArrayList;
import java.util.List;

public class Meal {
    private MealType mealType;
    private List<FoodItem> foodItems;

    public Meal(MealType mealType) {
        this.mealType = mealType;
        this.foodItems = new ArrayList<>();
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

    // Method to add a food item to the meal
    public void addFoodItem(FoodItem foodItem)  {
        foodItems.add(foodItem);
    }
    // method to remove food item
    public void removeFoodItem(FoodItem foodItem)   {
        foodItems.remove(foodItem);
    }
    public double getTotalCalories()    {
        double totalCalories = 0;
        for(FoodItem foodItem : foodItems)  {
            totalCalories += foodItem.getCalories();// Fetch calories from the API
        }
        return totalCalories;
    }
    public double getTotalCarbs()   {
        double totalCarbs = 0;
        for(FoodItem foodItem : foodItems)  {
            totalCarbs += foodItem.getCarbs();//Fetch carbs from the API
        }
        return totalCarbs;
    }

}
