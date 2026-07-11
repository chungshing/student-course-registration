package com.example.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping({ "/", "/home" })
    public String home(HttpSession session) {

        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }

        return "home";
    }
}