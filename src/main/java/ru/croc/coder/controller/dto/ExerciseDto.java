package ru.croc.coder.controller.dto;

import ru.croc.coder.domain.*;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.ProgrammingLanguage;

import javax.persistence.*;
import java.util.Set;

public class ExerciseDto {
    Long id;

    private Long authorId;

    private String description;

    private Integer maxAttempts;

    private DifficultyLevelOfExercise difficultyLevel;

    private ProgrammingLanguage programmingLanguage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public DifficultyLevelOfExercise getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevelOfExercise difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public ProgrammingLanguage getCodeProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setCodeProgrammingLanguage(ProgrammingLanguage codeProgrammingLanguage) {
        programmingLanguage = codeProgrammingLanguage;
    }
}
