package com.example.registration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registration.entity.Course;
import com.example.registration.exception.CourseNotFoundException;
import com.example.registration.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }
}
