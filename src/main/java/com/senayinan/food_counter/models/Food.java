package com.senayinan.food_counter.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
public class Food extends AbstractEntity{
    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull(message = "Food type is required!")
    private FoodType foodType;

    @ElementCollection
    private List<Nutrient> nutrients; // List of nutrients for this food item

    // USDA-specific fields
    private Long fdcId; // Unique ID from the USDA API (optional)

    public Food(String name, FoodType foodType, List<Nutrient> nutrients, Long fdcId) {
        this.name = name;
        this.foodType = foodType;
        this.nutrients = nutrients;
        this.fdcId = fdcId;
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

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
        this.fdcId = fdcId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", foodType=" + foodType +
                ", nutrients=" + nutrients +
                ", fdcId='" + fdcId + '\'' +
                '}';
    }
}
