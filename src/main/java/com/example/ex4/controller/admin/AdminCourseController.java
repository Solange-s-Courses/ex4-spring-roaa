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

/**
 * Controller for managing Course entities through the admin panel.
 * Provides functionality to list, create, update, and delete courses.
 */
@Controller
@RequestMapping("/admin-panel/courses")
public class AdminCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private DepartmentService departmentService;
    @Autowired
    private CourseInstanceService courseInstanceService;

    /**
     * Displays a list of all courses.
     *
     * @param model the model to populate with course data
     * @return the view name for listing courses
     */
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "admin/courses/list";
    }

    /**
     * Displays the form for creating a new course.
     *
     * @param model the model to populate with course and department data
     * @return the view name for the course creation form
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/courses/form";
    }

    /**
     * Displays the form for editing an existing course.
     *
     * @param id the ID of the course to edit
     * @param model the model to populate with course and department data
     * @return the view name for the course edit form
     * @throws IllegalArgumentException if the course ID is invalid
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));
        model.addAttribute("course", course);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "admin/courses/form";
    }

    /**
     * Handles the submission of the course form (for both create and edit).
     *
     * @param course the course object from the form
     * @param result the result of validation
     * @param model the model to repopulate if there are errors
     * @return redirect to course list on success, otherwise reload form
     */
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "admin/courses/form";
        }
        courseService.save(course);
        return "redirect:/admin-panel/courses";
    }

    /**
     * Deletes a course by ID, including all its instances.
     *
     * @param id the ID of the course to delete
     * @return redirect to the course list view
     */
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        Course course = courseService.findById(id).orElseThrow();
        List<CourseInstance> instances = courseInstanceService.findByCourse(course);
        courseInstanceService.deleteAll(instances);
        courseService.deleteById(id);
        return "redirect:/admin-panel/courses";
    }
}
