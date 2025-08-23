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

/**
 * Controller for handling student and lecturer views of their own courses.
 * <p>
 * Students can view and unregister from their enrolled courses.
 * Lecturers can view the courses they teach.
 * Credit tracking is also available for students.
 */
@Controller
public class MyCoursesController {

    @Resource
    private UserSession userSession;

    @Resource
    private EnrollmentService enrollmentService;

    @Resource
    private CourseInstanceService courseInstanceService;

    /**
     * Displays the "My Courses" page for the logged-in user.
     * <ul>
     *     <li>Students see their enrolled courses, earned and remaining credits.</li>
     *     <li>Lecturers see the list of courses they teach.</li>
     * </ul>
     *
     * @param model the Spring MVC model to populate attributes for rendering the view
     * @return the name of the Thymeleaf template to render
     */
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

        model.addAttribute("userName", userSession.getUsername());

        return "my-courses";
    }

    /**
     * Unregisters a student from a specific course instance.
     *
     * @param courseInstanceId the ID of the course instance to unregister from
     * @param redirectAttributes used to pass success message after redirection
     * @return redirect to the "My Courses" page
     */
    @PostMapping("/my-courses/unregister/{id}")
    public String unregisterFromCourse(@PathVariable("id") Long courseInstanceId,
                                       RedirectAttributes redirectAttributes) {
        Long studentId = userSession.getUserId();
        enrollmentService.deleteEnrollment(studentId, courseInstanceId);
        redirectAttributes.addFlashAttribute("unregisterSuccess", true);
        return "redirect:/my-courses";

    }

}
