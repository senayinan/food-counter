package com.senayinan.food_counter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FoodItem extends AbstractEntity{

    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    @NotBlank(message = "Name is required!")
    private String name;

    @NotNull(message = "Quantity is required!")
    @Min(value = 0, message = "Quantity must be non-negative")
    private double quantity;

    @NotNull(message = "Food type is required!")
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @NotNull(message = "Serving size is required!")
    @Enumerated(EnumType.STRING)
    private ServingSize servingSize;
    @ElementCollection
    private List<Nutrient> foodItemNutrients = new ArrayList<>(); // List to hold nutrient data

    private Long fdcId; // USDA Food Data Central ID


    //to reference meal
    @ManyToOne
    @JoinColumn(name = "meal_id") // Foreign key column in FoodItem table
    private Meal meal;


    public FoodItem(String name, double quantity, ServingSize servingSize, FoodType foodType,
                    Long fdcId) {
        this.name = name;
        this.quantity = quantity;
        this.servingSize = servingSize;
        this.foodType = foodType;
        this.fdcId = fdcId;
    }

    public FoodItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(ServingSize servingSize) {
        this.servingSize = servingSize;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Long getFdcId() {
        return fdcId;
    }

    public void setFdcId(Long fdcId) {
        this.fdcId = fdcId;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public List<Nutrient> getFoodItemNutrients() {
        return foodItemNutrients;
    }
    public void setFoodItemNutrients(List<Nutrient> foodItemNutrients) {
        this.foodItemNutrients = foodItemNutrients;
    }

    public void addNutrient(Nutrient nutrient) {
        this.foodItemNutrients.add(nutrient);
    }

    public void removeNutrient(Nutrient nutrient) {
        this.foodItemNutrients.remove(nutrient); // Remove the nutrient from the list
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "fdcId=" + fdcId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", foodType=" + foodType +
                ", servingSize=" + servingSize +
                ", foodNutrients=" + foodItemNutrients +
                '}';
    }

}

