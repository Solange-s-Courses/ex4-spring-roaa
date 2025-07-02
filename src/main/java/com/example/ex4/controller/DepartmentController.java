package com.example.ex4.controller;

import com.example.ex4.model.Department;
import com.example.ex4.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing departments.
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Returns a list of all departments.
     *
     * @return list of departments
     */
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    /**
     * Returns a specific department by ID.
     *
     * @param id department ID
     * @return the department if found
     */
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    /**
     * Creates a new department.
     *
     * @param department department to create
     * @return the saved department
     */
    @PostMapping
    public Department createDepartment(@Valid @RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    /**
     * Updates an existing department.
     *
     * @param id          department ID
     * @param updatedDept updated department data
     * @return the updated department
     */
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @Valid @RequestBody Department updatedDept) {
        Optional<Department> existingOpt = departmentService.getDepartmentById(id);
        if (existingOpt.isPresent()) {
            Department existing = existingOpt.get();
            existing.setName(updatedDept.getName());
            existing.setTotalCredits(updatedDept.getTotalCredits());
            existing.setDescription(updatedDept.getDescription());
            return departmentService.saveDepartment(existing);
        } else {
            throw new RuntimeException("Department not found with ID " + id);
        }
    }

    /**
     * Deletes a department by ID.
     *
     * @param id department ID
     */
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
