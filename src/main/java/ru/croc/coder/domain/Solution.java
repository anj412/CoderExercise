package ru.croc.coder.domain;

import ru.croc.coder.school.solutions.SolutionSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @JoinColumn(nullable = false)
    @ManyToOne
    private User author;
    
    private String description;

    @Column(name = "time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @Column
    @Enumerated(EnumType.STRING)
    private SolutionSize solutionSize;

    @Embedded
    private Code code;

    @Column(nullable = false)
    private Boolean passed = false;

    @Column
    @Enumerated(EnumType.STRING)
    private ProcessStatus checkStatus;

    public SolutionSize getSolutionSize() {
        return solutionSize;
    }
    public Solution setSolutionSize(SolutionSize solutionSize) {
        this.solutionSize = solutionSize;
        return this;
    }

    public Long getId() {
        return id;
    }
    public Solution setId(Long id) {
        this.id = id;
        return this;
    }

    public Exercise getExercise() {
        return exercise;
    }
    public Solution setExercise(Exercise exercise) {
        this.exercise = exercise;
        return this;
    }

    public User getAuthor() {
        return author;
    }
    public Solution setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public Solution setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public Solution setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Code getCode() {
        return code;
    }
    public Solution setCode(Code code) {
        this.code = code;
        return this;
    }

    public Boolean getPassed() {
        return passed;
    }
    public Solution setPassed(Boolean passed) {
        this.passed = passed;
        return this;
    }

    public ProcessStatus getCheckStatus() {
        return checkStatus;
    }
    public Solution setCheckStatus(ProcessStatus checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }
}