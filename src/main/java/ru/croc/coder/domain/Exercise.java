package ru.croc.coder.domain;


import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "EXERCISES")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(mappedBy = "choosenExercises")
    private Set<Course> usedCoutses;

    @OneToMany(mappedBy = "exercise")
    private Set<Solution> solutions;

    @Column(name = "description")
    @Lob
    private String description;


    //private String text;

    @Enumerated(EnumType.STRING)
    private DifficultyLevelOfExercise difficultyLevel;
  
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseAttachment> attachments;
        
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseData> dataSet;        
    
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseTestCase> testCases;
    
    @ManyToOne
    @JoinColumn (name = "restriction_id")
    private ExerciseRestriction restriction;
    
    public Long getId() { return id;}
    
    public String getDescription() { return description;}
    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }


    public DifficultyLevelOfExercise getDifficultyLevel() {return difficultyLevel;}
    public Exercise setDifficultyLevel(DifficultyLevelOfExercise difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }
}
