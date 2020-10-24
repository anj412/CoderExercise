package ru.croc.coder.domain;

import ru.croc.coder.school.exercises.AttachmentToExercise;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.TestCase;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private Set<Course> courseSet;

    @Transient
    private Solution solution;

    public Long getId() {
        return id;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }
    private DifficultyLevelOfExercise difficultyLevel;
    public DifficultyLevelOfExercise getDifficultyLevel() {
        return difficultyLevel;
    }
    public Exercise setDifficultyLevel(DifficultyLevelOfExercise difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }





    private ArrayList<AttachmentToExercise> attachments;

    private String enterData;
    private String exitData;
    private ArrayList<TestCase> testCases;
    private String referenceSolution;

    private LocalDate dateOpened;
    private LocalDate dateClosed;

    private Duration solutionDuration;

    private int maxRam;
    private int maxDiskStorage;


}
