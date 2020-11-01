package ru.croc.coder.domain;

import ru.croc.coder.school.solutions.SolutionSize;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solutions")
public class Solution {
    private SolutionSize solutionSize;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@OneToOne
    //@Transient
    @ManyToOne
	@JoinColumn (name = "exercise_id")
    Exercise exercise;

    @Transient
    private User author;
    
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;
}