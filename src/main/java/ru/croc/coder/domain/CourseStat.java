package ru.croc.coder.domain;

public class CourseStat {
    Long courseId;

    Long studentsN;
    Long teachersN;
    Long exercisesN;
    Long decidedExercisesN;

    public Long getCourseId() {
        return courseId;
    }

    public CourseStat setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public Long getStudentsN() {
        return studentsN;
    }

    public CourseStat setStudentsN(Long studentsN) {
        this.studentsN = studentsN;
        return this;
    }

    public Long getTeachersN() {
        return teachersN;
    }

    public CourseStat setTeachersN(Long teachersN) {
        this.teachersN = teachersN;
        return this;
    }

    public Long getExercisesN() {
        return exercisesN;
    }

    public CourseStat setExercisesN(Long exercisesN) {
        this.exercisesN = exercisesN;
        return this;
    }

    public Long getDecidedExercisesN() {
        return decidedExercisesN;
    }

    public CourseStat setDecidedExercisesN(Long decidedExercisesN) {
        this.decidedExercisesN = decidedExercisesN;
        return this;
    }
}
