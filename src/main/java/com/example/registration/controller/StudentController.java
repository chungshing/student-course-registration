package com.example.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.registration.entity.Student;
import com.example.registration.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        var students = studentService.getAllStudents().stream()
                .filter(s -> keyword == null || keyword.isBlank()
                        || s.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @PostMapping
    public String addStudent(
            @RequestParam("name") String name,
            @RequestParam("email") String email) {

        studentService.addStudent(new Student(name, email));
        return "redirect:/students";
    }
}
