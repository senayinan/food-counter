package com.senayinan.food_counter.models;

public enum FoodType {
    MEAT("Meat"),
    VEGETABLE("Vegetable"),
    FRUIT("Fruit"),
    GRAIN("Grain"),
    DAIRY("Dairy"),
    BEVERAGE("Beverage"),
    PROTEIN("Protein"),
    OTHER("OTHER");

    private final String typeOfFood;

    FoodType(String foodType) {
        this.typeOfFood = foodType;
    }

    public String getFoodType() {
        return typeOfFood;
    }
}
