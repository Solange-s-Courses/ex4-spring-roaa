package com.example.ex4.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.*;


@Entity
public class CourseInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Min(value = 2000, message = "Year must be a valid year from 2000 onward")
    @Max(value = 2100, message = "Year must be before 2100")
    @Column(nullable = false)
    private int year;

    @Min(value = 1, message = "Semester must be at least 1")
    @Max(value = 3, message = "Semester must be 1 (Fall), 2 (Spring), or 3 (Summer)")
    @Column(nullable = false)
    private int semester;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 1000, message = "Capacity is too high")
    @Column(nullable = false)
    private int capacity;

    @NotNull(message = "Course is required")
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @NotNull(message = "Lecturer is required")
    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private User lecturer;


    // Constructors, getters, setters
    public CourseInstance() {}

    public CourseInstance(Long id) {
        this.id = id;
    }

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


}
