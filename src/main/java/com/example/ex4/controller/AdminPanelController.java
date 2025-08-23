package com.example.ex4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for displaying the main Admin Panel page.
 * This page acts as an entry point for admin users to access management sections,
 * such as users, departments, courses, course instances, and enrollments.
 */
@Controller
public class AdminPanelController {

    /**
     * Handles requests to the main admin panel page.
     *
     * @return the name of the Thymeleaf template for the admin panel view
     */
    @GetMapping("/admin-panel")
    public String showAdminPanel() {
        return "admin-panel";
    }
}
