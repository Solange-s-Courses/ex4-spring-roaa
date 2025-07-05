package com.example.ex4.controller;

import com.example.ex4.model.Course;
import com.example.ex4.repository.CourseRepository;
import com.example.ex4.session.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing student's favorite courses stored in session.
 */
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final CourseRepository courseRepository;
    private final UserSession userSession;

    @Autowired
    public FavoriteController(CourseRepository courseRepository, UserSession userSession) {
        this.courseRepository = courseRepository;
        this.userSession = userSession;
    }

    /**
     * Add a course to the favorites list.
     *
     * @param courseId the ID of the course to add
     */
    @PostMapping("/{courseId}")
    public void addToFavorites(@PathVariable Long courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        courseOpt.ifPresent(userSession::addFavoriteCourse);
    }

    /**
     * Remove a course from the favorites list.
     *
     * @param courseId the ID of the course to remove
     */
    @DeleteMapping("/{courseId}")
    public void removeFromFavorites(@PathVariable Long courseId) {
        userSession.removeFavoriteCourse(courseId);
    }

    /**
     * Get all favorite courses for the current session.
     *
     * @return list of favorite courses
     */
    @GetMapping
    public List<Course> getFavoriteCourses() {
        return userSession.getFavoriteCourses();
    }
}
