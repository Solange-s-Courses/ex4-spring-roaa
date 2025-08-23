package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.service.CourseInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Course Instances entities via API.
 * Provides endpoints to perform CRUD operations on course Instances.
 */
@RestController
@RequestMapping("/api/course-instances")
public class CourseInstanceController {

    @Autowired
    private CourseInstanceService courseInstanceService;

    @GetMapping
    public List<CourseInstance> getAllInstances() {
        return courseInstanceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CourseInstance> getInstanceById(@PathVariable Long id) {
        return courseInstanceService.findById(id);
    }

    @PostMapping
    public CourseInstance createInstance(@Valid @RequestBody CourseInstance instance) {
        return courseInstanceService.save(instance);
    }

    @PutMapping("/{id}")
    public CourseInstance updateInstance(@PathVariable Long id, @Valid @RequestBody CourseInstance updated) {
        return courseInstanceService.updateCourseInstance(id, updated)
                .orElseThrow(() -> new RuntimeException("CourseInstance not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteInstance(@PathVariable Long id) {
        courseInstanceService.deleteById(id);
    }
}
