package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Solution;

public interface SolutionRepository extends CrudRepository<Solution, Long> {
}
