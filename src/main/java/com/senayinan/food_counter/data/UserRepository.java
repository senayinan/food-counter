package com.senayinan.food_counter.data;


import com.senayinan.food_counter.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);

}
