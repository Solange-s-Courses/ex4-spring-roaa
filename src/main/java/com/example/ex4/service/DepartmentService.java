package com.example.ex4.service;

import com.example.ex4.model.Department;
import com.example.ex4.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling department logic.
 */
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Returns all departments.
     */
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    /**
     * Finds a department by ID.
     */
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    /**
     * Saves a new or updated department.
     */
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Deletes a department by ID.
     */
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
