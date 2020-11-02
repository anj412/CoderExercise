package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;

public interface SolutionRepository extends CrudRepository<Solution, Long> {

    //@Query("select count(*) from solutions where author = {1} and problem = {2}")
    long countByAuthorAndExercise (User author, Exercise exercise);

    @Override
    @RestResource(exported = false)
    <S extends Solution> S save (S entity);
}
