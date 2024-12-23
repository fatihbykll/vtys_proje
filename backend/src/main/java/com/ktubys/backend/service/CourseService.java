package com.ktubys.backend.service;

import com.ktubys.backend.model.Course;
import com.ktubys.backend.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    
    @Autowired
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}