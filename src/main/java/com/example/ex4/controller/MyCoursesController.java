package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.session.UserSession;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyCoursesController {

    @Resource
    private UserSession userSession;

    @Resource
    private EnrollmentService enrollmentService;

    @Resource
    private CourseInstanceService courseInstanceService;

    @GetMapping("/my-courses")
    public String getMyCourses(Model model) {
        String role = userSession.getRole();
        Long userId = userSession.getUserId();

        List<CourseInstance> myCourses;

        switch (role) {
            case "STUDENT":
                myCourses = enrollmentService.getEnrolledCourseInstances(userId);
                model.addAttribute("studentCourses", myCourses);
                break;

            case "LECTURER":
                myCourses = courseInstanceService.getCourseInstancesByLecturer(userId);
                model.addAttribute("lecturerCourses", myCourses);
                break;

            default:
                model.addAttribute("error", "Unauthorized role");
                return "error";
        }

        model.addAttribute("fullName", userSession.getUsername()); // לשם תצוגה בעמוד

        return "my-courses";
    }
}
