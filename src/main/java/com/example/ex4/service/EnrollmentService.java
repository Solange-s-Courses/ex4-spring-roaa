package com.example.ex4.service;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.model.User;
import com.example.ex4.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ex4.repository.CourseInstanceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

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

    @Transactional
    public void deleteEnrollment(Long studentId, Long courseInstanceId) {
        enrollmentRepository.deleteByStudentIdAndCourseInstanceId(studentId, courseInstanceId);
    }

    /**
     * Attempts to enroll a student in a course instance with capacity checks.
     *
     * @param studentId         the ID of the student
     * @param courseInstanceId  the ID of the course instance
     * @return true if enrollment was successful, false if full
     */
    public boolean enrollStudentWithValidation(Long studentId, Long courseInstanceId) {

        // בדיקת עומס לפי capacity
        long currentEnrollmentCount = enrollmentRepository.findByCourseInstanceId(courseInstanceId).size();
        Optional<CourseInstance> optionalInstance = courseInstanceRepository.findById(courseInstanceId);

        if (optionalInstance.isEmpty()) return false;

        CourseInstance instance = optionalInstance.get();
        if (currentEnrollmentCount >= instance.getCapacity()) {
            return false;
        }

        // הרשמה בפועל
        Enrollment enrollment = new Enrollment();
        User student =new User(studentId);
        enrollment.setStudent(student);
        enrollment.setCourseInstance(instance);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment);
        return true;
    }


    public List<Enrollment> findByStudent(User student) {
        return enrollmentRepository.findByStudent(student);
    };

    public void deleteAll(List<Enrollment> enrollments) {
        enrollmentRepository.deleteAll(enrollments);
    }
    public List<Enrollment> findByCourseInstance(CourseInstance courseInstance) {
        return enrollmentRepository.findByCourseInstance(courseInstance);
    }



}
