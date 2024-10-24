package com.senayinan.food_counter.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FoodItemSearchResponse{
    @JsonProperty("foods")
    private List<FoodItem> foods;

    public List<FoodItem> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodItem> foods) {
        this.foods = foods;
    }
}
