package com.senayinan.food_counter.models;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK1("Snack1"),
    SNACK2("Snack2"),
    SNACK3("Snack3"),
    NONE("None");
    private final String mealType;

    MealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealType() {
        return mealType;
    }

    @Override
    public String toString() {
        return name();
    }
}
