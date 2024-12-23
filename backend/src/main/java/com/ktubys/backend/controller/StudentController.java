package com.ktubys.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/student")
public class StudentController {


    @GetMapping("/home")
    public String studentHome() {
        return "student_home"; 
    }    

     @PostMapping("/goToCourseSelection")
    public String goToCourseSelection() {
        return "redirect:/course/selection";
    }
}