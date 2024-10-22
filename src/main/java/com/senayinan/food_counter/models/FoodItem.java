package com.senayinan.food_counter.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class FoodItem extends AbstractEntity{

    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters!")
    @NotBlank(message = "Name is required!")
    private String name;
    @NotNull(message = "Quantity is required!")
    private double quantity;
    @NotNull(message = "Food type is required!")
    private FoodType foodType;
    @NotNull(message = "Serving size is required!")
    private ServingSize servingSize;

    public FoodItem(String name, double quantity, ServingSize servingSize, FoodType foodType) {
        this.name = name;
        this.quantity = quantity;
        this.servingSize = servingSize;
        this.foodType = foodType;
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

    // Method to fetch calories from the API
    public double getCalories() {
        return fetchNutritionalInfo("calories");
    }


    // Method to fetch carbs from the API
    public double getCarbs() {
        // Call the API to get the carb value
        return fetchNutritionalInfo("carbs");
    }

    public double getFats() {
        return fetchNutritionalInfo("fats");
    }
    // Method to fetch nutritional info based on type
    private double fetchNutritionalInfo(String nutrientType) {
        // Implement the API call here to get nutritional information
        // This is a placeholder; you will need to implement the actual API request.
        // For example:
        // return APIClient.getNutritionalInfo(apiId, nutrientType);

        // For now, return a dummy value (replace this with actual API logic)
        return 0.0; // Replace with actual API response
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", foodType=" + foodType +
                ", servingSize=" + servingSize +
                '}';
    }
}
