package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.session.UserSession;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

                int earnedCredits = myCourses.stream()
                        .mapToInt(ci -> ci.getCourse().getCredits())
                        .sum();

                int totalCredits = myCourses.stream()
                        .findFirst()
                        .map(ci -> ci.getCourse().getDepartment().getTotalCredits())
                        .orElse(140); // fallback if no department found

                int remainingCredits = Math.max(totalCredits - earnedCredits, 0);

                model.addAttribute("earnedCredits", earnedCredits);
                model.addAttribute("remainingCredits", remainingCredits);
                break;

            case "LECTURER":
                myCourses = courseInstanceService.getCourseInstancesByLecturer(userId);
                model.addAttribute("lecturerCourses", myCourses);
                break;

            default:
                model.addAttribute("error", "Unauthorized role");
                return "error";
        }

        model.addAttribute("userName", userSession.getUsername()); // לשם תצוגה בעמוד

        return "my-courses";
    }

    @PostMapping("/my-courses/unregister/{id}")
    public String unregisterFromCourse(@PathVariable("id") Long courseInstanceId,
                                       RedirectAttributes redirectAttributes) {
        Long studentId = userSession.getUserId();
        enrollmentService.deleteEnrollment(studentId, courseInstanceId);
        redirectAttributes.addFlashAttribute("unregisterSuccess", true);
        return "redirect:/my-courses";

    }

}
