package com.example.ex4.session;

import com.example.ex4.model.Course;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Stores user-specific session data like favorite courses.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {
    private Long userId;
    private String username;
    private String role;

    private Set<Course> favoriteCourses=new HashSet<>();

    public UserSession() {}

    /**
     * Add a course to favorites.
     *
     * @param course the course to add
     */
    public void addFavoriteCourse(Course course) {
        favoriteCourses.add(course);
    }

    /**
     * Remove a course by its ID from favorites.
     *
     * @param courseId the ID of the course to remove
     */
    public void removeFavoriteCourse(Long courseId) {
        favoriteCourses.removeIf(c -> c.getId().equals(courseId));
    }

    /**
     * Get all favorite courses in session.
     *
     * @return list of favorite courses
     */
    public List<Course> getFavoriteCourses() {
        return favoriteCourses.stream().collect(Collectors.toList());
    }

    /**
     * Check if a course is marked as favorite.
     *
     * @param courseId the ID of the course
     * @return true if the course is in favorites, false otherwise
     */
    public boolean isFavorite(Long courseId) {
        return favoriteCourses.stream().anyMatch(c -> c.getId().equals(courseId));
    }

    /**
     * Clear all favorite courses from the session.
     */
    public void clearFavorites() {
        favoriteCourses.clear();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }

}
