package com.example.namesa.repos;

import com.example.namesa.entities.Administrator;
import com.example.namesa.entities.Branch;
import com.example.namesa.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepo extends JpaRepository<Table, Long> {
    public List<Table> findAllByBranch(Branch branch);
    public Table findByCodeTableAndBranch(String codeTable, Branch branch);
}
