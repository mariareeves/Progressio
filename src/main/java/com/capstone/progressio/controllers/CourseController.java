package com.capstone.progressio.controllers;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //get courses by goal
    @GetMapping("/student/goal/{studentId}")
    public List<CourseDto> getCoursesByGoal(@PathVariable Long studentId){
        return courseService.getAllCoursesByGoal(studentId);
    }

    //get a note by id
    @GetMapping("/{courseId}")
    public Optional<CourseDto> getNoteById(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
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
