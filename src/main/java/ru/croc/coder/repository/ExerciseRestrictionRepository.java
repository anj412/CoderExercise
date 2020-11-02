package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import ru.croc.coder.domain.ExerciseRestriction;

import java.util.Optional;

public interface ExerciseRestrictionRepository extends CrudRepository<ExerciseRestriction, Long>{


}
