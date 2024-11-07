package com.senayinan.food_counter.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class USDAFoodNutrient {
    @JsonProperty("nutrientName")
    private String nutrientName;
    @JsonProperty("value")
    private double value;

    public USDAFoodNutrient(String nutrientName, double value) {
        this.nutrientName = nutrientName;
        this.value = value;
    }

    public USDAFoodNutrient() {}

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "USDAFoodNutrient{" +
                "nutrientName='" + nutrientName + '\'' +
                ", value=" + value +
                '}';
    }
}
