//package com.example.ex4.model;
//
//import jakarta.persistence.*;
//import java.util.List;
//
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String username;
//    private String password;
//    private String fullName;
//
//    @Enumerated(EnumType.STRING)
//    private Role role; // STUDENT, LECTURER, ADMIN
//
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Enrollment> enrollments;
//
//    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Course> coursesTaught;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    // Constructors
//    public User() {}
//
//    public User(String username, String password, String fullName, Role role, Department department) {
//        this.username = username;
//        this.password = password;
//        this.fullName = fullName;
//        this.role = role;
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
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public List<Enrollment> getEnrollments() {
//        return enrollments;
//    }
//
//    public void setEnrollments(List<Enrollment> enrollments) {
//        this.enrollments = enrollments;
//    }
//
//    public List<Course> getCoursesTaught() {
//        return coursesTaught;
//    }
//
//    public void setCoursesTaught(List<Course> coursesTaught) {
//        this.coursesTaught = coursesTaught;
//    }
//
//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
//}
package com.example.ex4.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role; // STUDENT, LECTURER, ADMIN

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Constructors
    public User() {}

    public User(String username, String password, String fullName, Role role, Department department) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.department = department;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
