package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Exercise;

import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
	Exercise findByDescription(String description);
    Optional<Exercise> findById(Long id);
}
