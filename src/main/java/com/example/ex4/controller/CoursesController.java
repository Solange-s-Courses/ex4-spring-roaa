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

/**
 * Controller for handling the /courses page functionality.
 * Enables students to view and enroll in course instances based on year and semester.
 */
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

    /**
     * Displays the courses page with optional filters for year and semester.
     * Filters out already enrolled courses for the logged-in student.
     *
     * @param year optional year filter
     * @param semester optional semester filter
     * @param model Spring Model to pass data to Thymeleaf
     * @return the courses view
     */
    @GetMapping
    public String showCoursesPage(@Valid @RequestParam(required = false) Integer year,
                                  @Valid @RequestParam(required = false) Integer semester,
                                  Model model) {

        Long studentId = userSession.getUserId();


        List<CourseInstance> enrolledCourses = enrollmentService.getEnrolledCourseInstances(studentId);


        List<CourseInstance> filteredCourses = courseInstanceService.findAll().stream()
                .filter(c -> (year == null || c.getYear() == year) &&
                        (semester == null || c.getSemester() == semester))
                .collect(Collectors.toList());


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

    /**
     * Enrolls the logged-in student into a course instance.
     * If successful, redirects back with a success flag. Otherwise, with a failure flag.
     *
     * @param courseInstanceId the course instance ID to enroll in
     * @param redirectAttributes flash attributes to pass success/failure flags
     * @return redirect to /courses page
     */
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
