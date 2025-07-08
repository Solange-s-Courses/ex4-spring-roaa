package com.example.ex4.repository;

import com.example.ex4.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseInstanceId(Long courseInstanceId);
    void deleteByStudentIdAndCourseInstanceId(Long studentId, Long courseInstanceId);
    boolean existsByStudentIdAndCourseInstanceId(Long studentId, Long courseInstanceId);

    long countByCourseInstanceId(Long courseInstanceId);


}
