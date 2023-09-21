package com.capstone.progressio.controllers;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //get all courses
    @GetMapping("/student/{studentId}")
    public List<CourseDto> getCoursesByStudent(@PathVariable Long studentId){
        return courseService.getAllCoursesByStudentId(studentId);
    }

    //add a course
    @PostMapping("/student/{studentId}")
    public void addCourse(@RequestBody CourseDto courseDto, @PathVariable Long studentId){
        courseService.addCourse(courseDto, studentId);
    }

    //delete a course
    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable Long courseId){
        courseService.deleteCourseById(courseId);
    }

    //update a course
    @PutMapping
    public void updateCourse(@RequestBody CourseDto courseDto){
        courseService.updateCourseById(courseDto);
    }
}
