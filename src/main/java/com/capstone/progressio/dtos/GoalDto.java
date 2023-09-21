package com.capstone.progressio.dtos;


import com.capstone.progressio.entities.Course;
import com.capstone.progressio.entities.Goal;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDto {
    private Long id;
    private Date initialDate;
    private Date finalDate;
    private String goalsDescription;
    private StudentDto studentDto;
    private CourseDto courseDto;

    public GoalDto(Goal goal){
        if(goal.getId() != null ){
            this.id = goal.getId();
        }
        if(goal.getInitialDate() != null){
            this.initialDate = goal.getInitialDate();
        }
        if(goal.getFinalDate() != null){
            this.finalDate = goal.getFinalDate();
        }
        if(goal.getGoalsDescription() != null){
            this.goalsDescription = goal.getGoalsDescription();
        }
    }
}
