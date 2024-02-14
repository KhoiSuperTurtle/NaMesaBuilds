package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import com.example.namesa.entities.RestaurantCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCompanyRepo extends JpaRepository<RestaurantCompany, Long> {
}
