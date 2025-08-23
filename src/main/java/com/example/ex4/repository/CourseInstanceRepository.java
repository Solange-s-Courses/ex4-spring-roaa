package com.example.ex4.repository;

import com.example.ex4.model.Course;
import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link CourseInstance} entities.
 * Provides methods to retrieve course instances by lecturer, course, year, and semester.
 */
@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    /**
     * Finds all course instances taught by a specific lecturer, using their ID.
     *
     * @param lecturerId the ID of the lecturer
     * @return list of course instances assigned to the lecturer
     */
    List<CourseInstance> findByLecturerId(Long lecturerId);

    /**
     * Finds all course instances offered in a specific academic year and semester.
     *
     * @param year     the academic year (e.g., 2025)
     * @param semester the semester number (e.g., 1 for Spring, 2 for Fall)
     * @return list of course instances matching the year and semester
     */
    List<CourseInstance> findByYearAndSemester(int year, int semester);

    /**
     * Finds all course instances by course.
     *
     * @param course the course to match
     * @return list of course instances associated with the course
     */
    List<CourseInstance> findByCourse(Course course);

    /**
     * Finds all course instances taught by a specific lecturer.
     *
     * @param lecturer the lecturer user object
     * @return list of course instances assigned to the lecturer
     */
    List<CourseInstance> findByLecturer(User lecturer);

}
