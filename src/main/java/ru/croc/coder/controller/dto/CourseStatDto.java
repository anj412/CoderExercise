package ru.croc.coder.controller.dto;

import java.util.Map;

public class CourseStatDto {

    Long courseId;
    Long studentsNumber;
    Long teachersNumber;
    Map<Long,Long> exStat;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Long studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public Long getTeachersNumber() {
        return teachersNumber;
    }

    public void setTeachersNumber(Long teachersNumber) {
        this.teachersNumber = teachersNumber;
    }

    public Map<Long, Long> getExStat() {
        return exStat;
    }

    public void setExStat(Map<Long, Long> exStat) {
        this.exStat = exStat;
    }
}
