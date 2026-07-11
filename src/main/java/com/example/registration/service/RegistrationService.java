package com.example.registration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.registration.entity.Course;
import com.example.registration.entity.Student;
import com.example.registration.entity.StudentCourse;
import com.example.registration.exception.DuplicateRegistrationException;
import com.example.registration.repository.StudentCourseRepository;

@Service
public class RegistrationService {

    private final StudentCourseRepository studentCourseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public RegistrationService(StudentCourseRepository studentCourseRepository,
            StudentService studentService,
            CourseService courseService) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public StudentCourse registerStudentToCourse(Long studentId, Long courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (studentCourseRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new DuplicateRegistrationException(studentId, courseId);
        }

        return studentCourseRepository.save(new StudentCourse(student, course));
    }

    public List<StudentCourse> getCoursesForStudent(Long studentId) {
        studentService.getStudentById(studentId);
        return studentCourseRepository.findByStudentId(studentId);
    }
}
