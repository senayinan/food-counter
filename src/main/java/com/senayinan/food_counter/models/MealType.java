package com.senayinan.food_counter.models;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK1("Snack1"),
    SNACK2("Snack2"),
    SNACK3("Snack3"),
    NONE("None");

    private final String displayName;

    MealType(String displayName)    {
    this.displayName = displayName;
    }

    public String getDisplayMealName()  {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

