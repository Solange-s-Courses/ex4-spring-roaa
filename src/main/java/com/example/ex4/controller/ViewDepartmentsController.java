package com.example.ex4.controller;

import com.example.ex4.model.Department;
import com.example.ex4.service.DepartmentService;
import com.example.ex4.model.Course;
import com.example.ex4.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller responsible for displaying departments and their associated courses
 * on the `/departments` page. Accessible to all logged-in users.
 */
@Controller
@RequestMapping("/departments")
public class ViewDepartmentsController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CourseService courseService;


    /**
     * Displays the departments page with a list of all departments and their respective courses.
     *
     * @param model the model to be populated with departments and courses
     * @return the name of the view to render (`departments.html`)
     */
    @GetMapping
    public String showDepartmentsPage(Model model) {
        // Retrieve all departments
        List<Department> departments = departmentService.getAllDepartments();

        // Retrieve all courses
        List<Course> allCourses = courseService.findAllCourses();

        // Group courses by their department ID
        Map<Long, List<Course>> departmentCoursesMap = allCourses.stream()
                .filter(course -> course.getDepartment() != null)
                .collect(Collectors.groupingBy(course -> course.getDepartment().getId()));

        // Add data to the model for rendering in the view
        model.addAttribute("departments", departments);
        model.addAttribute("departmentCoursesMap", departmentCoursesMap);

        return "departments";
    }
}
