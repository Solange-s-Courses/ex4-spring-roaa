//package com.example.ex4.controller;
//
//import com.example.ex4.model.CourseInstance;
//import com.example.ex4.model.Enrollment;
//import com.example.ex4.model.User;
//import com.example.ex4.session.UserSession;
//import com.example.ex4.service.CourseInstanceService;
//import com.example.ex4.service.EnrollmentService;
//import com.example.ex4.service.UserService;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/courses")
//public class CoursesController {
//
//    @Resource
//    private UserSession userSession;
//
//    @Resource
//    private CourseInstanceService courseInstanceService;
//
//    @Resource
//    private EnrollmentService enrollmentService;
//
//    @Resource
//    private UserService userService;
//
//    @GetMapping
//    public String showCoursesPage(@RequestParam(required = false) Integer year,
//                                  @RequestParam(required = false) Integer semester,
//                                  Model model) {
//
//        Long studentId = userSession.getUserId();
//
//        // כבר נרשם אליהם
//        List<CourseInstance> enrolledCourses = enrollmentService.getEnrolledCourseInstances(studentId);
//
//        // כל הקורסים לפי סינון (אם קיים)
//        List<CourseInstance> filteredCourses = courseInstanceService.findAll().stream()
//                .filter(c -> (year == null || c.getYear() == year) &&
//                        (semester == null || c.getSemester()==semester))
//                .collect(Collectors.toList());
//
//        // להסיר את הקורסים שהסטודנט כבר רשום אליהם
//        Set<Long> enrolledIds = enrolledCourses.stream().map(CourseInstance::getId).collect(Collectors.toSet());
//        List<CourseInstance> availableCourses = filteredCourses.stream()
//                .filter(c -> !enrolledIds.contains(c.getId()))
//                .collect(Collectors.toList());
//
//        model.addAttribute("availableCourses", availableCourses);
//        model.addAttribute("enrolledCourses", enrolledCourses);
//        model.addAttribute("fullName", userSession.getUsername());
//        model.addAttribute("selectedYear", year);
//        model.addAttribute("selectedSemester", semester);
//
//        return "courses";
//    }
//
//    @PostMapping("/enroll/{id}")
//    public String enrollInCourse(@PathVariable("id") Long courseInstanceId) {
//        Long studentId = userSession.getUserId();
//
//        Enrollment enrollment = new Enrollment();
//        enrollment.setStudent(new User(studentId));
//        enrollment.setCourseInstance(new CourseInstance(courseInstanceId));
//
//        enrollmentService.save(enrollment);
//
//        return "redirect:/courses";
//    }
//}
package com.example.ex4.controller;

import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.Enrollment;
import com.example.ex4.model.User;
import com.example.ex4.session.UserSession;
import com.example.ex4.service.CourseInstanceService;
import com.example.ex4.service.EnrollmentService;
import com.example.ex4.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showCoursesPage(@RequestParam(required = false) Integer year,
                                  @RequestParam(required = false) Integer semester,
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
        model.addAttribute("fullName", userSession.getUsername());
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSemester", semester);

        return "courses";
    }

    @PostMapping("/enroll/{id}")
    public String enrollInCourse(@PathVariable("id") Long courseInstanceId) {
        Long studentId = userSession.getUserId();

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(new User(studentId));
        enrollment.setCourseInstance(new CourseInstance(courseInstanceId));

        enrollmentService.save(enrollment);

        return "redirect:/courses";
    }


}
