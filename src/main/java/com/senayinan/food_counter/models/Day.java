package com.senayinan.food_counter.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Optional;

@Entity
public class Day extends AbstractEntity{
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private EnumMap<MealType, Optional<Meal>> meals;

    public Day() {}

    public Day(LocalDate date) {
        this.date = date;



        meals = new EnumMap<>(MealType.class);

        meals.put(MealType.BREAKFAST, Optional.empty());
        meals.put(MealType.LUNCH, Optional.empty());
        meals.put(MealType.DINNER, Optional.empty());
        meals.put(MealType.SNACK1, Optional.empty());
        meals.put(MealType.SNACK2, Optional.empty());
        meals.put(MealType.SNACK3, Optional.empty());
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EnumMap<MealType, Optional<Meal>> getMeals() {
        return meals;
    }

    public void setMeals(EnumMap<MealType, Optional<Meal>> meals) {
        this.meals = meals;
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
    public double getTotalFat() {
        double totalFat = 0;
        for (Optional<Meal> meal : meals.values()) {
            if (meal.isPresent()) {
                totalFat += meal.get().getTotalFat();
            }
        }
        return totalFat;
    }

    public void addMeal(MealType mealType, Meal meal)   {
        if(meals.containsKey(mealType)) {
            System.out.println("Replacing existing meal for: " + mealType);
        }
        meals.put(mealType, Optional.of(meal));

        }
    public Optional<Meal> getMeal(MealType mealType)    {
        return meals.get(mealType);

        }

    @Override
    public String toString() {
        return "Day{" +
                "date=" + date +
                ", user=" + user +
                ", meals=" + meals +
                '}';
    }
}

