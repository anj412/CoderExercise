package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import ru.croc.coder.school.exercises.ExerciseDataType;

@Entity
public class ExerciseData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Transient
	Exercise exercise;
	
	private ExerciseDataType exerciseDataType;
	
	private String description;
}
