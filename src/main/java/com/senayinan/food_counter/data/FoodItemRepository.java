package com.senayinan.food_counter.data;

import com.senayinan.food_counter.models.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Integer>   {

}
