package com.example.ex4.repository;

import com.example.ex4.model.Course;
import com.example.ex4.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartment(Department department);


}
