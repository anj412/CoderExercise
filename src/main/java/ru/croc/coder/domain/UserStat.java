package ru.croc.coder.domain;

public class UserStat {
    Long userId;
    Long easyDecidedExercisesN;
    Long averageDecidedExercisesN;
    Long hardDecidedExercisesN;

    public Long getUserId() {
        return userId;
    }

    public UserStat setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getEasyDecidedExercisesN() {
        return easyDecidedExercisesN;
    }

    public UserStat setEasyDecidedExercisesN(Long easyDecidedExercisesN) {
        this.easyDecidedExercisesN = easyDecidedExercisesN;
        return this;
    }

    public Long getAverageDecidedExercisesN() {
        return averageDecidedExercisesN;
    }

    public UserStat setAverageDecidedExercisesN(Long averageDecidedExercisesN) {
        this.averageDecidedExercisesN = averageDecidedExercisesN;
        return this;
    }

    public Long getHardDecidedExercisesN() {
        return hardDecidedExercisesN;
    }

    public UserStat setHardDecidedExercisesN(Long hardDecidedExercisesN) {
        this.hardDecidedExercisesN = hardDecidedExercisesN;
        return this;
    }
}
