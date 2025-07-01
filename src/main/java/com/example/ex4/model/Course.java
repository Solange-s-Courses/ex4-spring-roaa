package com.example.ex4.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int credits;
    private String syllabus;
    private boolean mandatory;

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
