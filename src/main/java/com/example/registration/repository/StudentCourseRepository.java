package com.example.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findByStudentId(Long studentId);

    List<StudentCourse> findByCourseId(Long courseId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
}
