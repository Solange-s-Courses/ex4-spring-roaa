package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.model.User;
import com.example.ex4.session.UserSession;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import com.example.ex4.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    @Resource
    private UserSession userSession;

    @Resource
    private CourseInstanceService courseInstanceService;

    @Resource
    private EnrollmentService enrollmentService;

    @Resource
    private UserService userService;

    @GetMapping
    public String showCoursesPage(@Valid @RequestParam(required = false) Integer year,
                                  @Valid @RequestParam(required = false) Integer semester,
                                  Model model) {

        Long studentId = userSession.getUserId();

        // קורסים שהסטודנט כבר רשום אליהם
        List<CourseInstance> enrolledCourses = enrollmentService.getEnrolledCourseInstances(studentId);

        // כל הקורסים לפי סינון (אם קיים)
        List<CourseInstance> filteredCourses = courseInstanceService.findAll().stream()
                .filter(c -> (year == null || c.getYear() == year) &&
                        (semester == null || c.getSemester() == semester))
                .collect(Collectors.toList());

        // להסיר את הקורסים שהסטודנט כבר רשום אליהם
        Set<Long> enrolledIds = enrolledCourses.stream().map(CourseInstance::getId).collect(Collectors.toSet());
        List<CourseInstance> availableCourses = filteredCourses.stream()
                .filter(c -> !enrolledIds.contains(c.getId()))
                .collect(Collectors.toList());

        model.addAttribute("availableCourses", availableCourses);
        model.addAttribute("userName", userSession.getUsername());
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSemester", semester);

        model.addAttribute("userSession", userSession);
        return "courses";
    }

    @PostMapping("/enroll/{id}")
    public String enrollInCourse(@PathVariable("id") Long courseInstanceId, RedirectAttributes redirectAttributes) {
        Long studentId = userSession.getUserId();


        boolean success = enrollmentService.enrollStudentWithValidation(studentId, courseInstanceId);

        if (success) {
            redirectAttributes.addFlashAttribute("success", true);
        } else {
            redirectAttributes.addFlashAttribute("failure", true);
        }

        return "redirect:/courses";
    }



}
