package com.senayinan.food_counter.models;

import jakarta.persistence.*;

@Embeddable
public class Nutrient {
    private String nutrientName;
    private Double amount;

    // Constructors
    public Nutrient() {
    }

    public Nutrient(String nutrientName, Double amount) {
        this.nutrientName = nutrientName;
        this.amount = amount;
    }

    // Getters and setters
    public String getNutrientName() {
        return nutrientName;
    }
    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
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
                "nutrientName='" + nutrientName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
