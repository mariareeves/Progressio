package com.capstone.progressio.services;

import com.capstone.progressio.dtos.CourseDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CourseService {
    //add a course
    @Transactional
    void addCourse(CourseDto courseDto, Long studentId);

    //delete a course
    @Transactional
    void deleteCourseById(Long courseId);

    //update a course
    @Transactional
    void updateCourseById(CourseDto courseDto);

    //validate priority
    default boolean isValidPriority(int priority) {
        return priority >= 1 && priority <= 5;
    }

    //find all courses by Student
    @Transactional
    List<CourseDto> getAllCoursesByStudentId(Long studentId);
}
