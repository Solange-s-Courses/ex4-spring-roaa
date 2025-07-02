package com.example.ex4.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "course_instance_id"})
        }
)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Student is required")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @NotNull(message = "Course instance is required")
    @ManyToOne
    @JoinColumn(name = "course_instance_id", nullable = false)
    private CourseInstance courseInstance;

    @NotNull(message = "Enrollment date is required")
    @Column(nullable = false)
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
