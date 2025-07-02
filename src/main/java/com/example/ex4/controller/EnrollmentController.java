package com.example.ex4.controller;

import com.example.ex4.model.Enrollment;
import com.example.ex4.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing course enrollments.
 */
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Get all enrollments in the system.
     */
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    /**
     * Get a specific enrollment by ID.
     */
    @GetMapping("/{id}")
    public Optional<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.findById(id);
    }

    /**
     * Create a new enrollment.
     */
    @PostMapping
    public Enrollment createEnrollment(@Valid @RequestBody Enrollment enrollment) {
        return enrollmentService.save(enrollment);
    }

    /**
     * Delete an enrollment by ID.
     */
    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteById(id);
    }

    /**
     * Get all enrollments by student ID.
     */
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return enrollmentService.findByStudentId(studentId);
    }

    /**
     * Get all enrollments by course instance ID.
     */
    @GetMapping("/course-instance/{instanceId}")
    public List<Enrollment> getEnrollmentsByCourseInstance(@PathVariable Long instanceId) {
        return enrollmentService.findByCourseInstanceId(instanceId);
    }
}
