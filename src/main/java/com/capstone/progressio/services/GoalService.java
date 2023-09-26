package com.capstone.progressio.services;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.dtos.GoalDto;
import com.capstone.progressio.entities.Course;
import jakarta.transaction.Transactional;

import java.util.List;

public interface GoalService {
    //add a goal
    @Transactional
    void addGoal(GoalDto goalDto, Long studentId);

    //update a goal
    @Transactional
    void updateGoal(GoalDto goalDto);

    //method to convert course set
    default Course convertCourseDtoToEntity(CourseDto courseDto) {
        Course course = new Course();

        course.setCourseName(courseDto.getCourseName());
        course.setInstitutionPlatform(courseDto.getInstitutionPlatform());
        course.setTotalHours(courseDto.getTotalHours());
        course.setHoursTaken(courseDto.getHoursTaken());
        course.setPriority(courseDto.getPriority());
        course.setStatus(courseDto.getStatus());

        return course;
    }

    //display all goals by student id
    @Transactional
    List<GoalDto> getAllGoalsByStudentId(Long studentId);

    // Do I need this method ?
    //display all courses by goal id and student id
    @Transactional
    List<CourseDto> getAllCoursesByGoalIdAndStudentId(Long goalId, Long studentId);
}
