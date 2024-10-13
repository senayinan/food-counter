package com.senayinan.food_counter.models;

public class Food {
    private final String Id;
    private String name;
    private FoodType foodType;

    public Food(String id, String name, FoodType foodType) {
        Id = id;
        this.name = name;
        this.foodType = foodType;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "Food{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", foodType=" + foodType +
                '}';
    }
}
