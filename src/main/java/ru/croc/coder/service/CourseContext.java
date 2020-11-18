package ru.croc.coder.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.croc.coder.domain.Course;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CourseContext {
    private Course currentCourse;

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public CourseContext setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
        return this;
    }
}
