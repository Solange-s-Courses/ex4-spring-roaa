//package com.example.ex4.model;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class Course {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    private int year;       // Year of study: 1, 2, 3...
//    private int semester;   // 1, 2, or summer
//    private int credits;    // Number of credit points
//    private int capacity;   // Max number of students
//
//    // @JoinColumn stores the names of the columns that will
//    // be linked to database tables (`lecturer_id`, `department_id`).
//    @ManyToOne
//    @JoinColumn(name = "lecturer_id")
//    private User lecturer;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Enrollment> enrollments;
//
//    // Constructors
//    public Course() {
//    }
//
//    public Course(String title, int year, int semester, int credits, int capacity, User lecturer, Department department) {
//        this.title = title;
//        this.year = year;
//        this.semester = semester;
//        this.credits = credits;
//        this.capacity = capacity;
//        this.lecturer = lecturer;
//        this.department = department;
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public int getSemester() {
//        return semester;
//    }
//
//    public void setSemester(int semester) {
//        this.semester = semester;
//    }
//
//    public int getCredits() {
//        return credits;
//    }
//
//    public void setCredits(int credits) {
//        this.credits = credits;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public User getLecturer() {
//        return lecturer;
//    }
//
//    public void setLecturer(User lecturer) {
//        this.lecturer = lecturer;
//    }
//
//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
//
//    public List<Enrollment> getEnrollments() {
//        return enrollments;
//    }
//
//    public void setEnrollments(List<Enrollment> enrollments) {
//        this.enrollments = enrollments;
//    }
//}
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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseInstance> instances;

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

    public List<CourseInstance> getInstances() { return instances; }
    public void setInstances(List<CourseInstance> instances) { this.instances = instances; }
}
