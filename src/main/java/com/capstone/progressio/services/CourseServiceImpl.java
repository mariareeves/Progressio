package com.capstone.progressio.services;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.entities.Course;
import com.capstone.progressio.entities.Student;
import com.capstone.progressio.repositories.CourseRepository;
import com.capstone.progressio.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;


    //add a course
    @Override
    @Transactional
    public void addCourse(CourseDto courseDto, Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Course course = new Course(courseDto);
        studentOptional.ifPresent(course::setStudent);
        courseRepository.saveAndFlush(course);
    }

    //delete a course
    @Override
    @Transactional
    public void deleteCourseById(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        courseOptional.ifPresent(course -> courseRepository.delete(course));
    }

    //update a course
    @Override
    @Transactional
    public void updateCourseById(CourseDto courseDto){
        if(courseDto.getId() == null){
            System.out.println("Student Id does not exist.");
            return;
        }

        if (!isValidPriority(courseDto.getPriority())) {
            System.out.println("Enter a number between 1 and 5.");
            return;
        }
        Optional<Course> courseOptional = courseRepository.findById(courseDto.getId());
        courseOptional.ifPresent(course -> {
            course.setCourseName(courseDto.getCourseName());
            course.setPriority(courseDto.getPriority());
            course.setTotalHours(courseDto.getTotalHours());
            course.setHoursTaken(courseDto.getHoursTaken());
            course.setInstitutionPlatform(courseDto.getInstitutionPlatform());
            course.setGoal(courseDto.getGoal());
            courseRepository.saveAndFlush(course);
        });
    }

    //find all courses by Student
    @Override
    @Transactional
    public List<CourseDto> getAllCoursesByStudentId(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            List<Course> courseList = courseRepository.findAllByStudentEquals(studentOptional.get());
            return courseList.stream().map(course -> new CourseDto(course)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<CourseDto> getCourseById(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(courseOptional.isPresent()){
            return Optional.of(new CourseDto(courseOptional.get()));
        }
        return Optional.empty();
    }

    //precisamos de um method para fornecer um endpoint que traga todos os cursos by student id and by isGoal true
    @Override
    @Transactional
    public List<CourseDto> getAllCoursesByGoal(Long studentId){
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            List<Course> courseList = courseRepository.findAllByStudentEquals(studentOptional.get());
            //filter courseList to get only the goal is set to true
            List<Course> filteredList = new ArrayList<>();
            for(Course course : courseList){
                if(course.getGoal().equals(true)){
                    filteredList.add(course);
                }
            }
            return filteredList.stream().map(course -> new CourseDto(course)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
