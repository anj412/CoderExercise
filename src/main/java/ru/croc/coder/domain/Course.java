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
    private Set<ExerciseCourseRegistration> exerciseRegistrations;

    @OneToMany(mappedBy = "course")
    private Set<UserCourseRegistration> userRegistrations;

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

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<ExerciseCourseRegistration> getExerciseRegistrations() {
        return exerciseRegistrations;
    }

    public Course setExerciseRegistrations(Set<ExerciseCourseRegistration> exerciseRegistrations) {
        this.exerciseRegistrations = exerciseRegistrations;
        return this;
    }

    public Set<UserCourseRegistration> getUserRegistrations() {
        return userRegistrations;
    }

    public Course setUserRegistrations(Set<UserCourseRegistration> userRegistrations) {
        this.userRegistrations = userRegistrations;
        return this;
    }
}
