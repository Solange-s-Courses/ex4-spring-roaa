package com.example.ex4.repository;

import com.example.ex4.model.Course;
import com.example.ex4.model.CourseInstance;
import com.example.ex4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByLecturerId(Long lecturerId);
    List<CourseInstance> findByYearAndSemester(int year, int semester);

    /**
     * Finds all course instances by course.
     *
     * @param course the course to match
     * @return list of course instances associated with the course
     */
    List<CourseInstance> findByCourse(Course course);
    List<CourseInstance> findByLecturer(User lecturer);

}
