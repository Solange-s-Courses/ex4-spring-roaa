package com.example.ex4.controller.admin;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Course;
import com.example.ex4.model.Enrollment;
import com.example.ex4.model.User;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.CourseService;
import com.example.ex4.service.EnrollmentService;
import com.example.ex4.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-panel/course-instances")
public class AdminCourseInstanceController {

    @Resource
    private CourseInstanceService courseInstanceService;

    @Resource
    private EnrollmentService enrollmentService;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;

    @GetMapping
    public String listInstances(Model model) {
        List<CourseInstance> instances = courseInstanceService.findAll();
        model.addAttribute("instances", instances);
        return "admin/course-instances/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("courseInstance", new CourseInstance());
        model.addAttribute("courses", courseService.findAllCourses());
        model.addAttribute("lecturers", userService.findLecturers());
        return "admin/course-instances/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CourseInstance instance = courseInstanceService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid instance ID: " + id));
        model.addAttribute("courseInstance", instance);
        model.addAttribute("courses", courseService.findAllCourses());
        model.addAttribute("lecturers", userService.findLecturers());
        return "admin/course-instances/form";
    }

    @PostMapping("/save")
    public String saveCourseInstance(@Valid @ModelAttribute CourseInstance courseInstance,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("courses", courseService.findAllCourses());
            model.addAttribute("lecturers", userService.findLecturers());
            return "admin/course-instances/form";
        }
        courseInstanceService.save(courseInstance);
        return "redirect:/admin-panel/course-instances";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteCourseInstance(@PathVariable Long id) {
//        courseInstanceService.deleteById(id);
//        return "redirect:/admin-panel/course-instances";
//    }
@GetMapping("/delete/{id}")
public String deleteCourseInstance(@PathVariable Long id) {
    CourseInstance courseInstance = courseInstanceService.findById(id).orElseThrow();

    // שלב 1: מחיקת כל ההרשמות לקורס זה
    List<Enrollment> enrollments = enrollmentService.findByCourseInstance(courseInstance);
    enrollmentService.deleteAll(enrollments);

    // שלב 2: מחיקת הקורס עצמו
    courseInstanceService.deleteById(id);

    return "redirect:/admin-panel/course-instances";
}

}
