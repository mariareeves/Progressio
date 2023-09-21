package com.capstone.progressio.dtos;

import com.capstone.progressio.entities.Course;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto implements Serializable {
    private Long id;
    private String courseName;
    private String institutionPlatform;
    private Double totalHours;
    private Double hoursTaken;

    @Min(1)
    @Max(5)
    private Integer priority;

    private String status;
    private StudentDto studentDto;

    public CourseDto(Course course){
        if(course.getId() != null){
            this.id = course.getId();
        }
        if(course.getCourseName() != null){
            this.courseName = course.getCourseName();
        }

        if (course.getInstitutionPlatform() != null) {
            this.institutionPlatform = course.getInstitutionPlatform();
        }

        if (course.getTotalHours() != null) {
            this.totalHours = course.getTotalHours();
        }

        if (course.getHoursTaken() != null) {
            this.hoursTaken = course.getHoursTaken();
        }

        if (course.getPriority() != null) {
            this.priority = course.getPriority();
        }

        if (course.getStatus() != null) {
            this.status = course.getStatus();
        }

    }


}
