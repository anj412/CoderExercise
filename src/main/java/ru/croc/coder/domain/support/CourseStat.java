package ru.croc.coder.domain.support;

import java.util.Map;

public class CourseStat {
    Long courseId;
    Long studentsNumber;
    Long teachersNumber;
    Map<Long,Long> exStat;

    public Long getCourseId() {
        return courseId;
    }

    public CourseStat setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public Long getStudentsNumber() {
        return studentsNumber;
    }

    public CourseStat setStudentsNumber(Long studentsNumber) {
        this.studentsNumber = studentsNumber;
        return this;
    }

    public Long getTeachersNumber() {
        return teachersNumber;
    }

    public CourseStat setTeachersNumber(Long teachersNumber) {
        this.teachersNumber = teachersNumber;
        return this;
    }

    public Map<Long, Long> getExStat() {
        return exStat;
    }

    public CourseStat setExStat(Map<Long, Long> exStat) {
        this.exStat = exStat;
        return this;
    }
}
