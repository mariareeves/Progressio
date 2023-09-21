package com.capstone.progressio.entities;
import com.capstone.progressio.dtos.GoalDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Goals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "initial_date")
    private Date initialDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "final_date")
    private Date finalDate;

    @Column(name = "goals_description", columnDefinition = "text")
    private String goalsDescription;

//    @OneToOne(mappedBy = "goal")
    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Course> courseSet = new HashSet<>();


    public Goal(GoalDto goalDto){
        if(goalDto.getInitialDate() != null){
            this.initialDate = goalDto.getInitialDate();
        }
        if(goalDto.getFinalDate() != null){
            this.finalDate = goalDto.getFinalDate();
        }
        if(goalDto.getGoalsDescription() != null){
            this.goalsDescription = goalDto.getGoalsDescription();
        }
    }
}
