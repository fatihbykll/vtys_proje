package com.ktubys.backend.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktubys.backend.model.Course;
import com.ktubys.backend.model.User;
import com.ktubys.backend.repository.UserRepository;
import com.ktubys.backend.service.CourseService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserRepository userRepository;
    private final CourseService courseService;

    public StudentController(UserRepository userRepository, CourseService courseService) {
        this.userRepository = userRepository;
        this.courseService = courseService;
    }

    @PostMapping("/{userId}/enroll")
    public User enrollInCourse(@PathVariable Long userId, @RequestParam Long courseId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if ("STUDENT".equals(user.getRole())) {
                Course course = courseService.getAllCourses()
                        .stream()
                        .filter(c -> c.getId().equals(courseId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Course not found"));
                user.getCourses().add(course);
                return userRepository.save(user);
            } else {
                throw new RuntimeException("User is not a student");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/{userId}/courses")
    public Set<Course> getStudentCourses(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && "STUDENT".equals(userOptional.get().getRole())) {
            return userOptional.get().getCourses();
        }
        throw new RuntimeException("User not found or not a student");
    }

    @GetMapping("/home")
    public String studentHome() {
        return "student_home"; // student_home.html dosyasını döner
    }
}