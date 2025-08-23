package com.example.ex4.controller.admin;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.model.User;
import com.example.ex4.model.Role;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import com.example.ex4.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Admin controller for managing system users (students, lecturers, admins).
 * Allows creation, editing, deletion, and listing of users in the system.
 */
@Controller
@RequestMapping("/admin-panel/users")
public class AdminUserController {

    @Resource
    private UserService userService;
    @Autowired
    private CourseInstanceService courseInstanceService;
    @Autowired
    private EnrollmentService enrollmentService;


    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "admin/users/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/users/form";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "admin/users/form";
        }
        userService.save(user);
        return "redirect:/admin-panel/users";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow();

        switch (user.getRole()) {
           case LECTURER -> {

            List<CourseInstance> taught =courseInstanceService.findByLecturer(user);
            courseInstanceService.deleteAll(taught);
           }
           case STUDENT -> {

            List<Enrollment> enrollments = enrollmentService.findByStudent(user);
            enrollmentService.deleteAll(enrollments);
           }

        }

        userService.deleteById(id);
        return "redirect:/admin-panel/users";
    }

}
