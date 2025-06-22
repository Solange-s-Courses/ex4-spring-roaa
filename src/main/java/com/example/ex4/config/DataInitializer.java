package com.example.ex4.config;

import com.example.ex4.model.*;
import com.example.ex4.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // === Create a department ===
        Department cs = new Department("BSc Computer Science", 140, "Complete 140 credits to graduate.");
        departmentRepository.save(cs);

        // === Create users ===
        User admin = new User("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN, cs);
        User lecturer = new User("lecturer", passwordEncoder.encode("lect123"), "Dr. Smith", Role.LECTURER, cs);
        User student = new User("student", passwordEncoder.encode("stud123"), "John Student", Role.STUDENT, cs);

        userRepository.saveAll(List.of(admin, lecturer, student));

        // === Create course ===
        Course course = new Course("Intro to Programming", 1, 1, 5, 30, lecturer, cs);
        courseRepository.save(course);

        // === Enroll student ===
        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        enrollmentRepository.save(enrollment);
    }
}
