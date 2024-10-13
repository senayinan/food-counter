package com.senayinan.food_counter.models;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Optional;

public class Day {
    private LocalDate date;
    private EnumMap<MealType, Optional<Meal>> meals;

    public Day(LocalDate date) {
        this.date = date;

        meals = new EnumMap<>(MealType.class);
        // Initialize all meal slots as Optional.empty() (meals/snacks are optional)
        meals.put(MealType.BREAKFAST, Optional.empty());
        meals.put(MealType.LUNCH, Optional.empty());
        meals.put(MealType.DINNER, Optional.empty());
        meals.put(MealType.SNACK1, Optional.empty());
        meals.put(MealType.SNACK2, Optional.empty());
        meals.put(MealType.SNACK3, Optional.empty());
    }


    public LocalDate getDate() {
        return date;
    }
    //get total calories
    public double getTotalCalories() {
        double totalCalories = 0;
        for (Optional<Meal> meal : meals.values()) {
            if (meal.isPresent()) {
                totalCalories += meal.get().getTotalCalories();
            }
        }
        return totalCalories;
    }

    public double getTotalCarbs() {
        double totalCarbs = 0;
        for (Optional<Meal> meal : meals.values()) {
            if (meal.isPresent()) {
                totalCarbs += meal.get().getTotalCarbs();
            }
        }
        return totalCarbs;
    }



        //Method to add a meal
    public void addMeal(MealType mealType, Meal meal)   {
        if(meals.containsKey(mealType)) {
            System.out.println("Replacing existing meal for: " + mealType);
        }
        meals.put(mealType, Optional.of(meal));

        }
        // Method to get a meal
    public Optional<Meal> getMeal(MealType mealType)    {
        return meals.get(mealType);

        }
    }

