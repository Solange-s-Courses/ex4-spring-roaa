package com.example.ex4.controller.admin;

import com.example.ex4.model.Course;
import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Department;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.CourseService;
import com.example.ex4.service.DepartmentService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing departments in the admin panel.
 * Provides functionality to list, create, edit, save and delete departments.
 */
@Controller
@RequestMapping("/admin-panel/departments")
public class AdminDepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private CourseService courseService;

    @Resource
    private CourseInstanceService courseInstanceService;

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/departments/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "admin/departments/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + id));
        model.addAttribute("department", department);
        return "admin/departments/form";
    }

    @PostMapping("/save")
    public String saveDepartment(@Valid @ModelAttribute Department department,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/departments/form";
        }
        departmentService.saveDepartment(department);
        return "redirect:/admin-panel/departments";
    }


@GetMapping("/delete/{id}")
public String deleteDepartment(@PathVariable Long id) {
    Department department = departmentService.findById(id);


    List<Course> courses = courseService.findByDepartment(department);

    for (Course course : courses) {

        List<CourseInstance> instances = courseInstanceService.findByCourse(course);
        courseInstanceService.deleteAll(instances);


        courseService.deleteById(course.getId());
    }


    departmentService.deleteDepartment(id);

    return "redirect:/admin-panel/departments";
}

}
