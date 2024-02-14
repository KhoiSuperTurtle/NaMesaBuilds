package com.example.namesa.repos;

import com.example.namesa.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepo extends JpaRepository<Visitor, Long> {
    public Visitor findVisitorByVisitorLogin(String visitorLogin);
}
