package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import com.example.namesa.entities.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDishRepo extends JpaRepository<RestaurantDish, Long> {
}
