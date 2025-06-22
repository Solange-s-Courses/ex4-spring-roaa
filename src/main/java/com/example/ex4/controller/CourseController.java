package com.example.ex4.controller;

import com.example.ex4.model.Course;
import com.example.ex4.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * Get all courses
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    /**
     * Get a course by its ID
     */
    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    /**
     * Create a new course
     */
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    /**
     * Update an existing course
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseService.findById(id);
        if (existingCourseOpt.isPresent()) {
            Course course = existingCourseOpt.get();
            course.setTitle(updatedCourse.getTitle());
            course.setYear(updatedCourse.getYear());
            course.setSemester(updatedCourse.getSemester());
            course.setCredits(updatedCourse.getCredits());
            course.setCapacity(updatedCourse.getCapacity());
            course.setLecturer(updatedCourse.getLecturer());
            course.setDepartment(updatedCourse.getDepartment());
            return courseService.save(course);
        } else {
            throw new RuntimeException("Course not found with id " + id);
        }
    }

    /**
     * Delete a course
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
    }
}
