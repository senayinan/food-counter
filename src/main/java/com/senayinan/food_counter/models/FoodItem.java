package com.senayinan.food_counter.models;

public class FoodItem {
    private String name;
    private FoodType foodType;
    private String apiId;
    private ServingSize servingSize;

    public FoodItem(String name, FoodType foodType, String apiId, ServingSize servingSize) {
        this.name = name;
        this.foodType = foodType;
        this.apiId = apiId;
        this.servingSize = servingSize;
    }

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

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(ServingSize servingSize) {
        this.servingSize = servingSize;
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
    // Method to fetch nutritional info based on type
    private double fetchNutritionalInfo(String nutrientType) {
        // Implement the API call here to get nutritional information
        // This is a placeholder; you will need to implement the actual API request.
        // For example:
        // return APIClient.getNutritionalInfo(apiId, nutrientType);

        // For now, return a dummy value (replace this with actual API logic)
        return 0.0; // Replace with actual API response
    }



}
