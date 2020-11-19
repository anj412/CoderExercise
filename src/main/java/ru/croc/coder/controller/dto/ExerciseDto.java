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

    private Code template;

    public Code getTemplate() {
        return template;
    }

    public ExerciseDto setTemplate(Code template) {
        this.template = template;
        return this;
    }

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


}
