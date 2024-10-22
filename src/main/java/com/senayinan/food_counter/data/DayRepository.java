package com.senayinan.food_counter.data;

import com.senayinan.food_counter.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends CrudRepository<Day, Integer> {
}
