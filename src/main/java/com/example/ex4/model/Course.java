package com.example.ex4.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.*;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course title is required")
    @Size(max = 100, message = "Course title cannot exceed 100 characters")
    @Column(nullable = false, unique = true)
    private String title;

    @Min(value = 1, message = "Credits must be at least 1")
    @Max(value = 20, message = "Credits must not exceed 20")
    @Column(nullable = false)
    private int credits;

    @NotBlank(message = "Syllabus is required")
    @Size(max = 1000, message = "Syllabus is too long")
    @Column(nullable = false)
    private String syllabus;

    @Column(nullable = false)
    private boolean mandatory;

    @NotNull(message = "Department is required")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    // Constructors
    public Course() {}

    public Course(String title, int credits, String syllabus, boolean mandatory, Department department) {
        this.title = title;
        this.credits = credits;
        this.syllabus = syllabus;
        this.mandatory = mandatory;
        this.department = department;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getSyllabus() { return syllabus; }
    public void setSyllabus(String syllabus) { this.syllabus = syllabus; }

    public boolean isMandatory() { return mandatory; }
    public void setMandatory(boolean mandatory) { this.mandatory = mandatory; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }


}
