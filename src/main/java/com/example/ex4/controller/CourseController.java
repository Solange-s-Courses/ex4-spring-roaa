package com.example.ex4.controller;

import com.example.ex4.model.Course;
import com.example.ex4.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Course entities via API.
 * Provides endpoints to perform CRUD operations on courses.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * Retrieves all courses.
     *
     * @return list of all courses
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course
     * @return Optional containing the course if found, or empty if not found
     */
    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    /**
     * Creates a new course.
     *
     * @param course the course to be created
     * @return the created course
     */
    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.save(course);
    }

    /**
     * Updates an existing course by ID.
     *
     * @param id the ID of the course to update
     * @param updatedCourse the updated course data
     * @return the updated course
     * @throws RuntimeException if the course with the given ID is not found
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @Valid @RequestBody Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseService.findById(id);
        if (existingCourseOpt.isPresent()) {
            Course course = existingCourseOpt.get();
            course.setTitle(updatedCourse.getTitle());
            course.setSyllabus(updatedCourse.getSyllabus());
            course.setCredits(updatedCourse.getCredits());
            course.setMandatory(updatedCourse.isMandatory());
            course.setDepartment(updatedCourse.getDepartment());
            return courseService.save(course);
        } else {
            throw new RuntimeException("Course not found with id " + id);
        }
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }
}
