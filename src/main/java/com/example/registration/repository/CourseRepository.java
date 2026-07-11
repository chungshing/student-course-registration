package com.example.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registration.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
