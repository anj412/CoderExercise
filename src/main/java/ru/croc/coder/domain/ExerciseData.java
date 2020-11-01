package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ru.croc.coder.school.exercises.ExerciseDataType;

@Entity
@Table(name = "DATA")
public class ExerciseData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "data_id")
	Exercise exercise;
	
	private ExerciseDataType exerciseDataType;
	
	private String description;
}
