package com.example.ex4.controller.admin;

import com.example.ex4.model.Course;
import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Department;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.CourseService;
import com.example.ex4.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-panel/courses")
public class AdminCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private DepartmentService departmentService;
    @Autowired
    private CourseInstanceService courseInstanceService;

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "admin/courses/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/courses/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
        model.addAttribute("course", course);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/courses/form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "admin/courses/form";
        }
        courseService.save(course);
        return "redirect:/admin-panel/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        Course course = courseService.findById(id).orElseThrow();
        List<CourseInstance> instances = courseInstanceService.findByCourse(course);
        courseInstanceService.deleteAll(instances);
        courseService.deleteById(id);
        return "redirect:/admin-panel/courses";
    }
}
