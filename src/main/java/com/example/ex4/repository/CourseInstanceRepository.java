package com.example.ex4.repository;

import com.example.ex4.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
}
