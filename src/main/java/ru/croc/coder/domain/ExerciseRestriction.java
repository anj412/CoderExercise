package ru.croc.coder.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "restrictions")
public class ExerciseRestriction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@OneToMany(mappedBy = "restriction")	
	private Set<Exercise> exercises;
		
    private LocalDateTime timeOpened;
    private LocalDateTime timeClosed;

    private Duration solutionDuration;
    private int maxRam;
    private int maxDiskStorage;

    public Long getId() {
        return id;
    }

    public ExerciseRestriction setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public ExerciseRestriction setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
        return this;
    }

    public LocalDateTime getTimeOpened() {
        return timeOpened;
    }

    public ExerciseRestriction setTimeOpened(LocalDateTime timeOpened) {
        this.timeOpened = timeOpened;
        return this;
    }

    public LocalDateTime getTimeClosed() {
        return timeClosed;
    }

    public ExerciseRestriction setTimeClosed(LocalDateTime timeClosed) {
        this.timeClosed = timeClosed;
        return this;
    }

    public Duration getSolutionDuration() {
        return solutionDuration;
    }

    public ExerciseRestriction setSolutionDuration(Duration solutionDuration) {
        this.solutionDuration = solutionDuration;
        return this;
    }

    public int getMaxRam() {
        return maxRam;
    }

    public ExerciseRestriction setMaxRam(int maxRam) {
        this.maxRam = maxRam;
        return this;
    }

    public int getMaxDiskStorage() {
        return maxDiskStorage;
    }

    public ExerciseRestriction setMaxDiskStorage(int maxDiskStorage) {
        this.maxDiskStorage = maxDiskStorage;
        return this;
    }
}
