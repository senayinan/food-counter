package com.senayinan.food_counter.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

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
    private Long fdcId; // USDA Food Data Central ID
    @ElementCollection
    private List<Nutrient> foodItemNutrients; // List to hold nutrient data


    public FoodItem(String name, double quantity, ServingSize servingSize, FoodType foodType,
                    Long fdcId) {
        this.name = name;
        this.quantity = quantity;
        this.servingSize = servingSize;
        this.foodType = foodType;
        this.fdcId = fdcId;
        this.foodItemNutrients = fetchNutritionalInfoFromUSDA(fdcId); // Fetch nutrients on creation
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

    public List<Nutrient> getFoodItemNutrients() {
        return foodItemNutrients;
    }

    public void setFoodNutrients(List<Nutrient> foodItemNutrients) {
        this.foodItemNutrients = foodItemNutrients;
    }

    // Method to fetch nutrients from USDA API based on fdcId
    private List<Nutrient> fetchNutritionalInfoFromUSDA(Long fdcId) {
        // Implement the API call here to get nutritional information
        // This is a placeholder; you will need to implement the actual API request.
        // Example:
        // return APIClient.getNutritionalInfo(fdcId);

        // For now, return an empty list (replace this with actual API logic)
        return List.of(); // Replace with actual API response
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
    //Nutrient class to hold nutrient data
    public static class Nutrient    {
        private String NutrientName;
        private Double amount;

        public String getNutrientName() {
            return NutrientName;
        }

        public void setNutrientName(String nutrientName) {
            NutrientName = nutrientName;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }


        @Override
        public String toString() {
            return "Nutrient{" +
                    "NutrientName='" + NutrientName + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}

