package com.senayinan.food_counter.data;

import com.senayinan.food_counter.models.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Long>   {

    // Method to find food items by the associated meal's ID
    List<FoodItem> findByMealId(Long mealId);

}
