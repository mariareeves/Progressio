package com.capstone.progressio.repositories;

import com.capstone.progressio.entities.Course;
import com.capstone.progressio.entities.Goal;
import com.capstone.progressio.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByStudentEquals(Student student);

}
