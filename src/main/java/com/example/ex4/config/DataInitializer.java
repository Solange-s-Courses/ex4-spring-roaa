//package com.example.ex4.config;
//
//import com.example.ex4.model.*;
//import com.example.ex4.repository.*;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class DataInitializer {
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private CourseInstanceRepository courseInstanceRepository;
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void init() {
//        // === Create a department ===
//        Department cs = new Department("BSc Computer Science", 140, "Complete 140 credits to graduate.");
//        departmentRepository.save(cs);
//
//        // === Create users ===
//        User admin = new User("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN);
//        User lecturer = new User("lecturer", passwordEncoder.encode("lect123"), "Dr. Smith", Role.LECTURER);
//        User student = new User("student", passwordEncoder.encode("stud123"), "John Student", Role.STUDENT);
//
//        userRepository.saveAll(List.of(admin, lecturer, student));
//
//
//        // Course definition
//        Course course = new Course("Intro to Programming",5, "Basics of Java", true, cs);
//        courseRepository.save(course);
//
//        // CourseInstance definition
//        CourseInstance instance = new CourseInstance(1, 1, 30, course, lecturer);
//        courseInstanceRepository.save(instance);
//
//        // Enrollment
//        Enrollment enrollment = new Enrollment(student, instance, LocalDate.now());
//        enrollmentRepository.save(enrollment);
//
//    }
//}
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

//    @PostConstruct
//    public void init() {
//
//        // === Create a department ===
//        Department cs = departmentRepository.findByName("BSc Computer Science")
//                .orElseGet(() -> departmentRepository.save(
//                        new Department("BSc Computer Science", 140, "Complete 140 credits to graduate.")
//                ));
//
//        // === Create users ===
//        if (userRepository.findByUsername("admin").isEmpty()) {
//            User admin = new User("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN);
//            userRepository.save(admin);
//        }
//
//        if (userRepository.findByUsername("lecturer").isEmpty()) {
//            User lecturer = new User("lecturer", passwordEncoder.encode("lect123"), "Dr. Smith", Role.LECTURER);
//            userRepository.save(lecturer);
//        }
//
//        if (userRepository.findByUsername("student").isEmpty()) {
//            User student = new User("student", passwordEncoder.encode("stud123"), "John Student", Role.STUDENT);
//            userRepository.save(student);
//        }
//
//        // === Create course if not exists ===
//        Optional<Course> existingCourseOpt = courseRepository.findAll().stream()
//                .filter(c -> c.getTitle().equalsIgnoreCase("Intro to Programming"))
//                .findFirst();
//
//        Course course = existingCourseOpt.orElseGet(() ->
//                courseRepository.save(new Course("Intro to Programming", 5, "Basics of Java", true, cs))
//        );
//
//        // === Create course instance if not exists ===
//        Optional<CourseInstance> existingInstanceOpt = courseInstanceRepository.findAll().stream()
//                .filter(i -> i.getCourse().getTitle().equals("Intro to Programming") && i.getYear() == 1 && i.getSemester() == 1)
//                .findFirst();
//
//        CourseInstance instance = existingInstanceOpt.orElseGet(() -> {
//            User lecturer = userRepository.findByUsername("lecturer").orElseThrow();
//            return courseInstanceRepository.save(new CourseInstance(1, 1, 30, course, lecturer));
//        });
//
//        // === Create enrollment if not exists ===
//        User student = userRepository.findByUsername("student").orElseThrow();
//        boolean alreadyEnrolled = enrollmentRepository.findByStudentId(student.getId()).stream()
//                .anyMatch(e -> e.getCourseInstance().getId().equals(instance.getId()));
//
//        if (!alreadyEnrolled) {
//            enrollmentRepository.save(new Enrollment(student, instance, LocalDate.now()));
//        }
//    }

    @PostConstruct
    public void init() {
        // === אם קיימת כבר מחלקה כלשהי – לא נאתחל שוב ===
        if (departmentRepository.count() > 0) return;

        // === Create department ===
        Department cs = new Department("BSc Computer Science", 140, "Complete 140 credits to graduate.");
        departmentRepository.save(cs);

        // === Create users ===
        User admin = new User("admin", passwordEncoder.encode("admin123"), "Admin User", Role.ADMIN);
        User lecturer = new User("lecturer", passwordEncoder.encode("lect123"), "Dr. Smith", Role.LECTURER);
        User student = new User("student", passwordEncoder.encode("stud123"), "John Student", Role.STUDENT);

        userRepository.saveAll(List.of(admin, lecturer, student));

        // === Create course ===
        Course course = new Course("Intro to Programming", 5, "Basics of Java", true, cs);
        courseRepository.save(course);

        // === Create course instance ===
        CourseInstance instance = new CourseInstance(1, 1, 30, course, lecturer);
        courseInstanceRepository.save(instance);

        // === Create enrollment ===
        enrollmentRepository.save(new Enrollment(student, instance, LocalDate.now()));
    }

}

