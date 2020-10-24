package ru.croc.coder.domain;

import ru.croc.coder.school.courses.CourseStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private CourseStatus courseStatus;
    private String description;

    @Transient
    private Set<Exercise> exerciseSet;

    @Transient
    private Set<User> accessedStudents;

    public String getDescription() { return description;}
    public Course setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() { return id; }

    public CourseStatus getCourseStatus() { return courseStatus; }

    public Course setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }





    public boolean checkCorrectCourseStatement() {
        //CODE HER
        return true;}
}
