package com.example.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registration.exception.CourseNotFoundException;
import com.example.registration.exception.DuplicateRegistrationException;
import com.example.registration.exception.StudentNotFoundException;
import com.example.registration.service.CourseService;
import com.example.registration.service.RegistrationService;
import com.example.registration.service.StudentService;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    public RegistrationController(RegistrationService registrationService,
            StudentService studentService,
            CourseService courseService) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("studentId") Long studentId,
            @RequestParam("courseId") Long courseId,
            Model model) {

        try {
            registrationService.registerStudentToCourse(studentId, courseId);
        } catch (StudentNotFoundException | CourseNotFoundException | DuplicateRegistrationException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("courses", courseService.getAllCourses());
            return "register";
        }

        return "redirect:/register";
    }

    @GetMapping("/student-courses")
    public String studentCourses(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        var registrations = registrationService.getAllRegistrations().stream()
                .filter(r -> keyword == null || keyword.isBlank()
                        || r.getStudent().getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        model.addAttribute("registrations", registrations);
        model.addAttribute("keyword", keyword);

        return "student-courses";
    }
}
