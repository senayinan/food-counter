package com.senayinan.food_counter.models;


import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meal extends AbstractEntity{

    @NotNull(message = "Meal type is required")
    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "At least one food item is required")
    private List<FoodItem> foodItems = new ArrayList<>();


    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;



    // Constructor for single food item
    public Meal(MealType mealType, FoodItem foodItem, LocalDate date) {
        this.mealType = mealType;
        this.date = date;
        this.foodItems = new ArrayList<>();
        this.foodItems.add(foodItem);
        foodItem.setMeal(this); // Maintain the bidirectional relationship
    }

    // Constructor for multiple food items
    public Meal(MealType mealType, List<FoodItem> foodItems, LocalDate date) {
        this.mealType = mealType;
        this.date = date;
        this.foodItems = foodItems != null ? foodItems : new ArrayList<>();
        for (FoodItem foodItem : this.foodItems) {
            foodItem.setMeal(this); // Maintain the bidirectional relationship
        }
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

    // Add a FoodItem to the meal and set its meal reference
    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
        foodItem.setMeal(this); // Set the Meal in the FoodItem
    }

    // Remove a FoodItem from the meal and clear its meal reference
    public void removeFoodItem(FoodItem foodItem) {
        foodItems.remove(foodItem);
        foodItem.setMeal(null); // Remove the Meal reference from the FoodItem
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meal{" +
                ", mealType=" + mealType +
                ", foodItems=" + foodItems +
                '}';
    }

}
