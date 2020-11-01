package ru.croc.coder.domain;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ATTACHMENTS")
public class ExerciseAttachment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@Transient
	@ManyToOne 
	@JoinColumn (name = "exercise_id")
	private Exercise exercise;
	
	private String description;
	
	public Exercise getExercise() {
		return exercise;
	}

	public ExerciseAttachment setExercise(Exercise exercise) {
		this.exercise = exercise;
		return this;
	}

	

	public String getDescription() {
		return description;
	}

	public ExerciseAttachment setDescription(String description) {
		this.description = description;
		return this;
	}

	public Long getId() {
		return id;
	}	

	

}
