package com.example.ex4.service;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }


    public List<Enrollment> findByCourseInstanceId(Long courseInstanceId) {
       return enrollmentRepository.findByCourseInstanceId(courseInstanceId);
    }


    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    /**
     * Returns the list of CourseInstances a student is enrolled in.
     *
     * @param studentId the ID of the student
     * @return list of enrolled CourseInstance objects
     */
    public List<CourseInstance> getEnrolledCourseInstances(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(Enrollment::getCourseInstance)
                .collect(Collectors.toList());
    }

    public List<Enrollment> getEnrollmentsByCourseInstanceId(Long courseInstanceId) {
        return enrollmentRepository.findByCourseInstanceId(courseInstanceId);
    }


}
