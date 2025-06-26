package com.example.ex4.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CourseInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private int semester;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private User lecturer;

    @OneToMany(mappedBy = "courseInstance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    // Constructors, getters, setters
    public CourseInstance() {}

    public CourseInstance(int year, int semester, int capacity, Course course, User lecturer) {
        this.year = year;
        this.semester = semester;
        this.capacity = capacity;
        this.course = course;
        this.lecturer = lecturer;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public User getLecturer() { return lecturer; }
    public void setLecturer(User lecturer) { this.lecturer = lecturer; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }
}
