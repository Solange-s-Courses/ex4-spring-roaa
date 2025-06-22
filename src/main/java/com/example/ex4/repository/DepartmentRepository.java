package com.example.ex4.repository;

import com.example.ex4.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Department entity.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // We can add custom queries here if needed later
}
