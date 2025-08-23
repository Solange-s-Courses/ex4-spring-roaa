package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import com.example.ex4.session.UserSession;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller responsible for displaying the students enrolled in a specific course instance.
 * Only lecturers who are assigned to the course can view the list of students.
 */
@Controller
public class CourseViewController {

    @Resource
    private CourseInstanceService courseInstanceService;

    @Resource
    private EnrollmentService enrollmentService;

    @Resource
    private UserSession userSession;

    /**
     * Displays the list of students enrolled in a specific course instance.
     * Accessible only by the lecturer assigned to the course instance.
     *
     * @param id the ID of the course instance
     * @param model the Spring Model used to pass attributes to the view
     * @return the course-students view if authorized, otherwise an error view
     */
    @GetMapping("/course/{id}")
    public String viewCourseStudents(@PathVariable Long id, Model model) {
        CourseInstance courseInstance = courseInstanceService.findById(id).orElse(null);

        if (courseInstance == null || !courseInstance.getLecturer().getId().equals(userSession.getUserId())) {
            model.addAttribute("error", "You are not authorized to view this course.");
            return "error";
        }

        List<Enrollment> enrollments = enrollmentService.findByCourseInstanceId(id);

        model.addAttribute("courseInstance", courseInstance);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("fullName", userSession.getUsername());
        return "course-students";
    }
}
