package ru.croc.coder.controller.dto;

public class CourseStatDto {

    Long courseId;

    Long studentsN;
    Long teachersN;
    Long exercisesN;
    Long decidedExercisesN;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentsN() {
        return studentsN;
    }

    public void setStudentsN(Long studentsN) {
        this.studentsN = studentsN;
    }

    public Long getTeachersN() {
        return teachersN;
    }

    public void setTeachersN(Long teachersN) {
        this.teachersN = teachersN;
    }

    public Long getExercisesN() {
        return exercisesN;
    }

    public void setExercisesN(Long exercisesN) {
        this.exercisesN = exercisesN;
    }

    public Long getDecidedExercisesN() {
        return decidedExercisesN;
    }

    public void setDecidedExercisesN(Long decidedExercisesN) {
        this.decidedExercisesN = decidedExercisesN;
    }
}
