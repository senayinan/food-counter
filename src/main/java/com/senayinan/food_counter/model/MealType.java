package com.senayinan.food_counter.model;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK1("Snack1"),
    SNACK2("Snack2"),
    SNACK3("Snack3"),
    NONE("None");
    private final String typeOfMeal;

    MealType(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }
}
