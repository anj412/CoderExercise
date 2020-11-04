package ru.croc.coder.domain;

import ru.croc.coder.school.courses.CourseStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    private String description;

    @OneToMany(mappedBy = "course")
    private Set<ExerciseCourseRegistration> exercises;

    @OneToMany(mappedBy = "course")
    Set<UserCourseRegistration> registrations;

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
   
}
