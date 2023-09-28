package com.capstone.progressio.entities;

import com.capstone.progressio.dtos.CourseDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "institution_platform")
    private String institutionPlatform;

    @Column(name = "total_hours")
    private Double totalHours;

    @Column(name = "hours_taken")
    private Double hoursTaken;

    @Column
    @Min(1)
    @Max(5)
    private Integer priority;

    @Column
    private String status;

    @Column
    private Boolean goal;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "student_id")
    private Student student;



    public Course(CourseDto courseDto){
        if(courseDto.getCourseName() != null){
            this.courseName = courseDto.getCourseName();
        }

        if (courseDto.getInstitutionPlatform() != null) {
            this.institutionPlatform = courseDto.getInstitutionPlatform();
        }

        if (courseDto.getTotalHours() != null) {
            this.totalHours = courseDto.getTotalHours();
        }

        if (courseDto.getHoursTaken() != null) {
            this.hoursTaken = courseDto.getHoursTaken();
        }

        if(courseDto.getPriority() != null){
            this.priority =  courseDto.getPriority();
        }

        if (courseDto.getStatus() != null) {
            this.status = courseDto.getStatus();
        }
        if(courseDto.getGoal() != null){
            this.goal = courseDto.getGoal();
        }
    }
}
