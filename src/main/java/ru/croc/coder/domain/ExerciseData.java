package ru.croc.coder.domain;

import javax.persistence.*;

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
	

	@Enumerated(EnumType.STRING)
	private ExerciseDataType exerciseDataType;
	
	private String description;
}
