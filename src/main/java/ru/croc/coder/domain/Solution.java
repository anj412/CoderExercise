package ru.croc.coder.domain;

import ru.croc.coder.school.solutions.SolutionSize;

import javax.persistence.*;

@Entity
public class Solution {
    private SolutionSize solutionSize;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    Exercise exercise;


}