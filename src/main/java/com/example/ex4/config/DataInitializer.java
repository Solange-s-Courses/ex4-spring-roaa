package com.example.ex4.config;

import com.example.ex4.model.*;
import com.example.ex4.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Configuration class responsible for initializing default data in the database
 * when the application starts.
 * This includes:
 * - Creating an admin user if it does not already exist.
 */
@Configuration
public class DataInitializer {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Initializes default data after the Spring context has been created.
     * This method is automatically invoked once after dependency injection is complete.
     * It creates a default admin user if not already present in the database.
     */
    @PostConstruct
    public void init() {

        // === Create Admin ===
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN);
            userRepository.save(admin);
        }

    }


}

