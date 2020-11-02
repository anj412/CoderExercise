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

    @ManyToMany
    @JoinTable(
    		  name = "exercise_choosen", 
    		  joinColumns = @JoinColumn(name = "exercise_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Exercise> choosenExercises;

    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations;

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
