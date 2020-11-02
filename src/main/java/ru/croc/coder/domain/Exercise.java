package ru.croc.coder.domain;


import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "EXERCISES")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@JoinColumn(nullable = false)
    @JoinColumn(nullable = false)
    @ManyToOne
    private User author;
    
    @ManyToMany(mappedBy = "choosenExercises")
    private Set<Course> usedCourses;

    @OneToMany(mappedBy = "exercise")
    private Set<Solution> solutions;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @Embedded
    private Code template;

    @Column(nullable = false)
    private Integer maxAttempts;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficultyLevelOfExercise difficultyLevel;
  
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseAttachment> attachments;
        
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseData> dataSet;        
    
    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseTestCase> testCases;
    
    @ManyToOne
    @JoinColumn (name = "restriction_id")
    private ExerciseRestriction restriction;
    
    public Long getId() { return id;}
    
    public String getDescription() { return description;}
    public Exercise setDescription(String description) {
        this.description = description;
        return this;
    }


    public DifficultyLevelOfExercise getDifficultyLevel() {return difficultyLevel;}
    public Exercise setDifficultyLevel(DifficultyLevelOfExercise difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }

    public User getAuthor() {
        return author;
    }
    public Exercise setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Exercise setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<Course> getUsedCourses() {
        return usedCourses;
    }

    public Exercise setUsedCourses(Set<Course> usedCourses) {
        this.usedCourses = usedCourses;
        return this;
    }

    public Set<Solution> getSolutions() {
        return solutions;
    }

    public Exercise setSolutions(Set<Solution> solutions) {
        this.solutions = solutions;
        return this;
    }

    public Code getTemplate() {
        return template;
    }

    public Exercise setTemplate(Code template) {
        this.template = template;
        return this;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public Exercise setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }

    public Set<ExerciseAttachment> getAttachments() {
        return attachments;
    }

    public Exercise setAttachments(Set<ExerciseAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Set<ExerciseData> getDataSet() {
        return dataSet;
    }

    public Exercise setDataSet(Set<ExerciseData> dataSet) {
        this.dataSet = dataSet;
        return this;
    }

    public Set<ExerciseTestCase> getTestCases() {
        return testCases;
    }

    public Exercise setTestCases(Set<ExerciseTestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    public ExerciseRestriction getRestriction() {
        return restriction;
    }

    public Exercise setRestriction(ExerciseRestriction restriction) {
        this.restriction = restriction;
        return this;
    }

}
