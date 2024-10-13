package com.senayinan.food_counter.models;

public enum ServingSize {
    CUP("Cup"),
    GRAMS("Grams"),
    OUNCES("Ounces"),
    TABLESPOON("Tablespoon"),
    TEASPOON("Teaspoon");

    private final String unit;

    ServingSize(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
