package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.ProcessStatus;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SolutionRepository extends CrudRepository<Solution, Long> {

    long countByAuthorAndExercise (User author, Exercise exercise);

    long countByAuthorAndExerciseAndPassed(User author, Exercise exercise, Boolean passed);

    long countByExercise_AuthorAndExerciseAndExercise_DifficultyLevelAndPassed(User author,
                                                                               Exercise exercise,
                                                                               DifficultyLevelOfExercise difficultyLevel,
                                                                               Boolean passed);



    default boolean passedExerciseByUser(User author, Exercise exercise) {
        return countByAuthorAndExerciseAndPassed(author, exercise, true) > 0;
    }

    @Override
    @RestResource(exported = false)
    <S extends Solution> S save (S entity);

    Optional<Solution> findTopByCheckStatus(ProcessStatus checkStatus);
    default Optional<Solution> findAnyQueued() {
        return findTopByCheckStatus(ProcessStatus.QUEUED);
    }


}
