package ru.croc.coder.controller.dto;

public class UserStatDto {
    Long userId;
    Long easyDecidedExercisesNumber;
    Long averageDecidedExercisesNumber;
    Long hardDecidedExercisesNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEasyDecidedExercisesNumber() {
        return easyDecidedExercisesNumber;
    }

    public void setEasyDecidedExercisesNumber(Long easyDecidedExercisesNumber) {
        this.easyDecidedExercisesNumber = easyDecidedExercisesNumber;
    }

    public Long getAverageDecidedExercisesNumber() {
        return averageDecidedExercisesNumber;
    }

    public void setAverageDecidedExercisesNumber(Long averageDecidedExercisesNumber) {
        this.averageDecidedExercisesNumber = averageDecidedExercisesNumber;
    }

    public Long getHardDecidedExercisesNumber() {
        return hardDecidedExercisesNumber;
    }

    public void setHardDecidedExercisesNumber(Long hardDecidedExercisesNumber) {
        this.hardDecidedExercisesNumber = hardDecidedExercisesNumber;
    }
}
