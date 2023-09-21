package com.capstone.progressio.services;

import com.capstone.progressio.dtos.StudentDto;
import com.capstone.progressio.entities.Student;
import com.capstone.progressio.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //handles registering a new student
    @Override
    @Transactional
    public List<String> addStudent(StudentDto studentDto){
        List<String> response = new ArrayList<>();
        Student student = new Student(studentDto);
        studentRepository.saveAndFlush(student);
        response.add("Student added successfully.");
        return response;
    }

    //allows student to log in to an existing account
    @Override
    public List<String> studentLogin(StudentDto studentDto){
        List<String> response = new ArrayList<>();
        Optional<Student> studentOptional = studentRepository.findByUsername(studentDto.getUsername());
        if(studentOptional.isPresent()){
            if(passwordEncoder.matches(studentDto.getPassword(), studentOptional.get().getPassword())){
                response.add("User login successful.");
                response.add(String.valueOf(studentOptional.get().getId()));
            } else {
                response.add("Username or password incorrect.");
            }
        } else {
            response.add("Username or password incorrect.");
        }
        return response;
    }

}
