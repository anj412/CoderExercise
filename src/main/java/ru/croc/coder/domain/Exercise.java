package ru.croc.coder.domain;


import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.TestCase;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Set<Course> courseSet;

    @Transient
    private Solution solution; 

    private String description;
    private DifficultyLevelOfExercise difficultyLevel;
  
    @Transient
    private Set<ExerciseAttachment> attachmentSet;
    
    @Transient
    private Set<ExerciseData> dataSet;
    
    @Transient
    private Set<TestCase> testCaseSet;
    
    
    private String enterData;
    private String exitData;
    
    private String referenceSolution;

    private LocalDate dateOpened;
    private LocalDate dateClosed;

    private Duration solutionDuration;

    private int maxRam;
    private int maxDiskStorage;
    
    public Long getId() { return id;}
    
    public String getDescription() { return description;}

    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }
    

    public DifficultyLevelOfExercise getDifficultyLevel() {
        return difficultyLevel;
    }
    public Exercise setDifficultyLevel(DifficultyLevelOfExercise difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }
}
