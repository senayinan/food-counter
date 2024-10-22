package com.senayinan.food_counter.data;

import com.senayinan.food_counter.models.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {
}
