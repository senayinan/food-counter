package com.senayinan.food_counter.models;

public enum ServingSize {
    CUP("Cup"),
    GRAMS("Grams"),
    KILOGRAMS("Kilograms"),
    OUNCES("Ounces"),
    TABLESPOON("Tablespoon"),
    TEASPOON("Teaspoon");

    private final String displayName;

    ServingSize(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
