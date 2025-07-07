package com.example.ex4.repository;

import com.example.ex4.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByLecturerId(Long lecturerId);
    List<CourseInstance> findByYearAndSemester(int year, int semester);


}
