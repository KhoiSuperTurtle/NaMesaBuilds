package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import com.example.namesa.entities.BranchDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchDishesRepo extends JpaRepository<BranchDish, Long> {
}
