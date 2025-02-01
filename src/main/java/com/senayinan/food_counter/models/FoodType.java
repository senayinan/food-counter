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

   private final String displayName;

    FoodType(String displayName) {
        this.displayName= displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {return displayName;}
}
