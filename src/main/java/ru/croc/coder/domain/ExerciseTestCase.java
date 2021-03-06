package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tests")
public class ExerciseTestCase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	//@Transient
	@ManyToOne
	@JoinColumn (name = "exercise_id")
	Exercise exercise;
	
	private String description;

}
