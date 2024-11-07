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
    private EnumMap<MealType, Meal> meals;

    public Day() {
        meals = new EnumMap<>(MealType.class);
        initializeMeals();
    }

    public Day(LocalDate date) {
        this.date = date;
        meals = new EnumMap<>(MealType.class);
        initializeMeals();
    }

        private void initializeMeals() {
            for (MealType mealType : MealType.values()) {
                meals.put(mealType, null); // Initialize with null to indicate no meal assigned
            }
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

    public EnumMap<MealType, Meal> getMeals() {
        return meals;
    }

    public void setMeals(EnumMap<MealType, Meal> meals) {
        this.meals = meals;
    }

    public LocalDate getDate() {
        return date;
    }


    public void addMeal(MealType mealType, Meal meal)   {
        if(meals.containsKey(mealType)) {
            System.out.println("Replacing existing meal for: " + mealType);
        }
        meals.put(mealType, meal);

        }
    public Meal getMeal(MealType mealType)    {
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

