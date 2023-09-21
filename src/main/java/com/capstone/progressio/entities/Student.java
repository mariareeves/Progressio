package com.capstone.progressio.entities;

import com.capstone.progressio.dtos.StudentDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Course> courseSet = new HashSet<>();

    //what params I need to pass in OneToOne ?
   @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
   @PrimaryKeyJoinColumn
//   @JoinColumn(name = "goal_id", referencedColumnName = "id")
   private Goal goal;

    public Student(StudentDto studentDto){
        if(studentDto.getUsername() != null){
            this.username = studentDto.getUsername();
        }
        if(studentDto.getPassword() != null){
            this.password = studentDto.getPassword();
        }
    }
}
