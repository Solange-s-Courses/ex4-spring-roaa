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

@Controller
@RequestMapping("/departments")
public class ViewDepartmentsController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CourseService courseService;


    @GetMapping
    public String showDepartmentsPage(Model model) {
        // שליפת כל המחלקות
        List<Department> departments = departmentService.getAllDepartments();

        // שליפת כל הקורסים
        List<Course> allCourses = courseService.findAllCourses();

        // יצירת מפה: מחלקה → רשימת הקורסים שלה
        Map<Long, List<Course>> departmentCoursesMap = allCourses.stream()
                .filter(course -> course.getDepartment() != null)
                .collect(Collectors.groupingBy(course -> course.getDepartment().getId()));

        model.addAttribute("departments", departments);
        model.addAttribute("departmentCoursesMap", departmentCoursesMap);

        return "departments";
    }
}
