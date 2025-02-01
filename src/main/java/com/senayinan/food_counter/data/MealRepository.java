package com.senayinan.food_counter.data;

import com.senayinan.food_counter.models.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
    List<Meal> findByDate(LocalDate date);  // Custom method to find meals by date
}
