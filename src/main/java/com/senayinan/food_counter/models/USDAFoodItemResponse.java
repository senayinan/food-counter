package com.senayinan.food_counter.models;

import java.util.List;

public class USDAFoodItemResponse extends AbstractEntity {
    private String fdcId;
    private String description;
    private List<USDAFoodNutrient> foodNutrients;

    public USDAFoodItemResponse(String fdcId, String description, List<USDAFoodNutrient> foodNutrients) {
        this.fdcId = fdcId;
        this.description = description;
        this.foodNutrients = foodNutrients;
    }

    public USDAFoodItemResponse() {}

    public String getFdcId() {
        return fdcId;
    }

    public void setFdcId(String fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<USDAFoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<USDAFoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    @Override
    public String toString() {
        return "USDAFoodItemResponse{" +
                "fdcId='" + fdcId + '\'' +
                ", description='" + description + '\'' +
                ", foodNutrients=" + foodNutrients +
                '}';
    }
}

