package com.ktubys.backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktubys.backend.model.User;
import com.ktubys.backend.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String getUserDashboard(Model model, Principal principal) {
        
        User user = userService.getUserByUsername(principal.getName());
        
        if (user == null) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
        if (user.getRole() != Role.STUDENT) {
            return"redirect:/instructor/home"; 
        }

        model.addAttribute("user", user);
        return "student-dashboard";
    }

    @PostMapping("/updateProfile")
    public String updateUserProfile(@ModelAttribute User user) {
        userService.updateUserProfile(user); 
        return "redirect:/user/dashboard"; 
    }
}
