package ru.croc.coder.controller.dto;

import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserCourseRegistrationDto {
    private Long id;

    Long courseId;

    Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
