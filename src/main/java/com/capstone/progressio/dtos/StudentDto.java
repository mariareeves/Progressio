package com.capstone.progressio.dtos;
import com.capstone.progressio.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<CourseDto> courseDtoSet = new HashSet<>();

    public StudentDto(Student student){
        if(student.getId() != null){
            this.id = student.getId();
        }
        if(student.getUsername() != null){
            this.username = student.getUsername();
        }
        if(student.getPassword() != null){
            this.password = student.getPassword();
        }
    }
}
