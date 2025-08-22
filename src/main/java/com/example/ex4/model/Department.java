package com.example.ex4.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.*;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name cannot exceed 100 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @Min(value = 1, message = "Total credits must be at least 1")
    @Max(value = 200, message = "Total credits must not exceed 200")
    @Column(nullable = false)
    private int totalCredits;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description is too long")
    @Column(nullable = false)
    private String description;


    // Constructors
    public Department() {
    }

    public Department(String name, int totalCredits, String description) {
        this.name = name;
        this.totalCredits = totalCredits;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
