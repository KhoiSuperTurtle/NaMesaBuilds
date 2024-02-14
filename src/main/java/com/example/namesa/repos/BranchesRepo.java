package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import com.example.namesa.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchesRepo extends JpaRepository<Branch, Long> {
    public Branch findBranchByIdBranch(int idBranch);
}
