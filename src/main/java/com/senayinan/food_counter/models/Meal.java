package com.senayinan.food_counter.models;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meal extends AbstractEntity{

    @NotNull(message = "Meal type is required")
    private MealType mealType;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "At least one food item is required")
    private List<FoodItem> foodItems;

    // Add the Day reference for the many-to-one relationship
    @ManyToOne
    @JoinColumn(name = "day_id") // Ensure this matches your database schema
    private Day day;



    // Constructor for single food item
    public Meal(MealType mealType, FoodItem foodItem) {
        this.mealType = mealType;
        this.foodItems = new ArrayList<>();
        this.foodItems.add(foodItem);
    }

    // Constructor for multiple food items
    public Meal(MealType mealType, List<FoodItem> foodItems) {
        this.mealType = mealType;
        this.foodItems = foodItems;
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

    @Override
    public String toString() {
        return "Meal{" +
                ", mealType=" + mealType +
                ", foodItems=" + foodItems +
                '}';
    }

}
