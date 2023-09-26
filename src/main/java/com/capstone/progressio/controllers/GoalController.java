package com.capstone.progressio.controllers;

import com.capstone.progressio.dtos.CourseDto;
import com.capstone.progressio.dtos.GoalDto;
import com.capstone.progressio.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    @Autowired
    private GoalService goalService;

    //get all goals
    @GetMapping("/student/{studentId}")
    public List<GoalDto> getGoalByStudent(@PathVariable Long studentId){
        return goalService.getAllGoalsByStudentId(studentId);
    }

    //add a goal
    @PostMapping("/student/{studentId}")
    public void addGoal(@RequestBody GoalDto goalDto, @PathVariable Long studentId){
        goalService.addGoal(goalDto, studentId);
    }

    //update a goal
    @PutMapping
    public void updateGoal(@RequestBody GoalDto goalDto){
        goalService.updateGoal(goalDto);
    }

    //get all courses by goal
}
