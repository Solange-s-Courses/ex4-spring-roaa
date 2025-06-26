package com.example.ex4.service;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.repository.CourseInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling CourseInstance business logic.
 */
@Service
public class CourseInstanceService {

    private final CourseInstanceRepository courseInstanceRepository;

    @Autowired
    public CourseInstanceService(CourseInstanceRepository courseInstanceRepository) {
        this.courseInstanceRepository = courseInstanceRepository;
    }

    /**
     * Returns all course instances in the system.
     */
    public List<CourseInstance> findAll() {
        return courseInstanceRepository.findAll();
    }

    /**
     * Finds a course instance by its ID.
     */
    public Optional<CourseInstance> findById(Long id) {
        return courseInstanceRepository.findById(id);
    }

    /**
     * Saves a new or updated course instance.
     */
    public CourseInstance save(CourseInstance instance) {
        return courseInstanceRepository.save(instance);
    }

    /**
     * Deletes a course instance by its ID.
     */
    public void deleteById(Long id) {
        courseInstanceRepository.deleteById(id);
    }

    /**
     * Updates an existing course instance.
     */
    public Optional<CourseInstance> updateCourseInstance(Long id, CourseInstance updated) {
        return courseInstanceRepository.findById(id).map(existing -> {
            existing.setYear(updated.getYear());
            existing.setSemester(updated.getSemester());
            existing.setCapacity(updated.getCapacity());
            existing.setLecturer(updated.getLecturer());
            existing.setCourse(updated.getCourse());
            return courseInstanceRepository.save(existing);
        });
    }
}
