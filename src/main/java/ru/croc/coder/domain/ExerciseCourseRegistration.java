package ru.croc.coder.domain;

import javax.persistence.*;

@Entity
@Table(name = "Education")
public class ExerciseCourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public ExerciseCourseRegistration setId(Long id) {
        this.id = id;
        return this;
    }

    public Course getCourse() {
        return course;
    }

    public ExerciseCourseRegistration setCourse(Course course) {
        this.course = course;
        return this;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public ExerciseCourseRegistration setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }
}
