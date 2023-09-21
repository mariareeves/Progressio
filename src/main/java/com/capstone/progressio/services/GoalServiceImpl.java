package com.capstone.progressio.services;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.dtos.GoalDto;
import com.capstone.progressio.entities.Course;
import com.capstone.progressio.entities.Goal;
import com.capstone.progressio.entities.Student;
import com.capstone.progressio.repositories.CourseRepository;
import com.capstone.progressio.repositories.GoalRepository;
import com.capstone.progressio.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    //add a goal
    @Override
    @Transactional
    public void addGoal(GoalDto goalDto, Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Goal goal = new Goal(goalDto);
        studentOptional.ifPresent(goal::setStudent);
        goalRepository.saveAndFlush(goal);
    }

    //update a goal
    @Override
    @Transactional
    public void updateGoal(GoalDto goalDto, List<CourseDto> courseDtoList) {
        Optional<Goal> goalOptional = goalRepository.findById(goalDto.getId());
        goalOptional.ifPresent(goal -> {
            goal.setGoalsDescription(goalDto.getGoalsDescription());
            goal.setInitialDate(goalDto.getInitialDate());
            goal.setFinalDate(goalDto.getFinalDate());

            // Convert CourseDto objects to Course entities
            Set<Course> courses = new HashSet<>();
            for (CourseDto courseDto : courseDtoList) { // Change the variable name here
                Course course = convertCourseDtoToEntity(courseDto);
                courses.add(course);
            }

            // Update courseSet
            goal.setCourseSet(courses);
        });
    }

    //display all goals by student id
    @Override
    @Transactional
    public List<GoalDto> getAllGoalsByStudentId(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            List<Goal> goalList = goalRepository.findAllByStudentEquals(studentOptional.get());
            return goalList.stream().map(goal -> new GoalDto(goal)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    // Do I need this method ?
    //display all courses by goal id and student id
    @Override
    @Transactional
    public List<CourseDto> getAllCoursesByGoalIdAndStudentId(Long goalId, Long studentId){
        //Find the student by studentId
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        //check if the student exists
        if(studentOptional.isPresent()){
            //find the goal by goalId
            Optional<Goal> goalOptional = goalRepository.findById(goalId);

            //check if the goal exists
            if(goalOptional.isPresent()){
                //Retrieve the goal from the optional
                Goal goal = goalOptional.get();

                //check if the goal is associated with the specified student
                if(goal.getStudent().equals(studentOptional.get())){
                    //find all courses associated with the goal
                    List<Course> courseList = courseRepository.findAllByGoalEquals(goal);
                    //map the courses to CourseDto objs
                    return courseList.stream().map(course -> new CourseDto(course)).collect(Collectors.toList());
                }
            }
        }

        return Collections.emptyList();
    }

}
