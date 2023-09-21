package com.capstone.progressio.controllers;

import com.capstone.progressio.dtos.StudentDto;
import com.capstone.progressio.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //post request to register a student
    @PostMapping("/register")
    public List<String> addStudent(@RequestBody StudentDto studentDto){
        String passHash = passwordEncoder.encode(studentDto.getPassword());
        studentDto.setPassword(passHash);
        return studentService.addStudent(studentDto);
    }

    //post request to login a student
    @PostMapping("/login")
    public List<String> studentLogin(@RequestBody StudentDto studentDto){
        return studentService.studentLogin(studentDto);
    }
}
