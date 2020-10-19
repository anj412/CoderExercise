package ru.croc.coder.school.courses;


import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Exercise {
    private String description;
    private ArrayList<AttachmentToExercise> attachments;
    private DifficultyLevelOfExercise difficultyLevel;
    private String enterData;
    private String exitData;
    private ArrayList<TestCase> testCases;
    private String referenceSolution;

    LocalDate dateOpened;
    LocalDate dateClosed;

    Duration solutionDuration;

    int maxRam;
    int maxDiskStorage;




}
