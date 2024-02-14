package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorsRepo extends JpaRepository<Administrator, Long> {
}
