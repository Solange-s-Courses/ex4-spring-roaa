package com.example.ex4.repository;

import com.example.ex4.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Department entity.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    /**
     * Finds a department by its name.
     *
     * @param name the name of the department
     * @return Optional containing the department if found, or empty otherwise
     */
    Optional<Department> findByName(String name);
}
