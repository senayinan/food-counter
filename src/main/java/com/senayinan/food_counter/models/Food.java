package com.senayinan.food_counter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Food extends AbstractEntity{
    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull(message = "Food type is required!")
    private FoodType foodType;

    public Food(String name, FoodType foodType) {
        this.name = name;
        this.foodType = foodType;
    }

    public Food() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "Food{" +
                ", name='" + name + '\'' +
                ", foodType=" + foodType +
                '}';
    }

}
