package com.capstone.progressio.services;

import com.capstone.progressio.dtos.StudentDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudentService {
    //handles registering a new student
    @Transactional
    List<String> addStudent(StudentDto studentDto);

    //allows student to log in to an existing account
    List<String> studentLogin(StudentDto studentDto);
}
