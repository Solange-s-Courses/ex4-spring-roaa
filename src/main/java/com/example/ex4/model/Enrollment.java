package com.example.ex4.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;


    @ManyToOne
    @JoinColumn(name = "course_instance_id")
    private CourseInstance courseInstance;


    private LocalDate enrollmentDate;

    // Constructors
    public Enrollment() {}


    public Enrollment(User student, CourseInstance courseInstance, LocalDate enrollmentDate) {
         this.student = student;
         this.courseInstance = courseInstance;
         this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }


    public CourseInstance getCourseInstance() {
        return courseInstance;
    }


    public void setCourseInstance(CourseInstance courseInstance) {
       this.courseInstance = courseInstance;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
