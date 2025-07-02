package com.example.ex4.controller;

import com.example.ex4.model.Course;
import com.example.ex4.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;

/**
 * Controller for managing courses.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.save(course);
    }

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

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }
}
