package ru.croc.coder.domain.stat;

public class UserStat {
    Long userId;
    Long easyDecidedExercisesNumber;
    Long averageDecidedExercisesNumber;
    Long hardDecidedExercisesNumber;

    public Long getUserId() {
        return userId;
    }

    public UserStat setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getEasyDecidedExercisesNumber() {
        return easyDecidedExercisesNumber;
    }

    public UserStat setEasyDecidedExercisesNumber(Long easyDecidedExercisesNumber) {
        this.easyDecidedExercisesNumber = easyDecidedExercisesNumber;
        return this;
    }

    public Long getAverageDecidedExercisesNumber() {
        return averageDecidedExercisesNumber;
    }

    public UserStat setAverageDecidedExercisesNumber(Long averageDecidedExercisesNumber) {
        this.averageDecidedExercisesNumber = averageDecidedExercisesNumber;
        return this;
    }

    public Long getHardDecidedExercisesNumber() {
        return hardDecidedExercisesNumber;
    }

    public UserStat setHardDecidedExercisesNumber(Long hardDecidedExercisesNumber) {
        this.hardDecidedExercisesNumber = hardDecidedExercisesNumber;
        return this;
    }
}
