package com.example.ex4.controller.admin;

import com.example.ex4.model.Enrollment;
import com.example.ex4.service.EnrollmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for managing enrollments in the admin panel.
 * Provides functionality to view and delete student enrollments.
 */
@Controller
@RequestMapping("/admin-panel/enrollments")
public class AdminEnrollmentController {

    @Resource
    private EnrollmentService enrollmentService;

    @GetMapping
    public String listEnrollments(Model model) {
        List<Enrollment> enrollments = enrollmentService.findAll();
        model.addAttribute("enrollments", enrollments);
        return "admin/enrollments/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteById(id);
        return "redirect:/admin-panel/enrollments";
    }
}
