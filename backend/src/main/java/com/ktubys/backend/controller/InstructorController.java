package com.ktubys.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @GetMapping("/home")
    public String instructorHome() {
        return "instructor_home"; // instructor_home.html dosyasını döner
    }
}