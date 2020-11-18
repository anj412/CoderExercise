package ru.croc.coder.controller.dto;

import ru.croc.coder.school.courses.CourseStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CourseDto {
    private Long id;

    private CourseStatus courseStatus;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
