package ru.croc.coder.controller.dto;

public class UserStatDto {
    Long userId;
    Long easyDecidedExercisesN;
    Long averageDecidedExercisesN;
    Long hardDecidedExercisesN;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEasyDecidedExercisesN() {
        return easyDecidedExercisesN;
    }

    public void setEasyDecidedExercisesN(Long easyDecidedExercisesN) {
        this.easyDecidedExercisesN = easyDecidedExercisesN;
    }

    public Long getAverageDecidedExercisesN() {
        return averageDecidedExercisesN;
    }

    public void setAverageDecidedExercisesN(Long averageDecidedExercisesN) {
        this.averageDecidedExercisesN = averageDecidedExercisesN;
    }

    public Long getHardDecidedExercisesN() {
        return hardDecidedExercisesN;
    }

    public void setHardDecidedExercisesN(Long hardDecidedExercisesN) {
        this.hardDecidedExercisesN = hardDecidedExercisesN;
    }
}
