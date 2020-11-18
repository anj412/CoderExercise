package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
	//Exercise findByDescription(String description);
    Optional<Exercise> findById(Long id);

    Set<Exercise> findBySolutions_Author_IdAndSolutions_PassedAndDifficultyLevel(Long id, Boolean passed, DifficultyLevelOfExercise difficultyLevel);

    default Long userStat(Long userId, DifficultyLevelOfExercise level) {
        return (long)(findBySolutions_Author_IdAndSolutions_PassedAndDifficultyLevel(userId, true, level).
                size());
    }

    Long countBySolutions_Author_IdAndSolutions_PassedAndDifficultyLevel(Long id, Boolean passed, DifficultyLevelOfExercise difficultyLevel);

   /* default Long userStat(Long userId, DifficultyLevelOfExercise level) {
        return countBySolutions_Author_IdAndSolutions_PassedAndDifficultyLevel(userId, true, level);
    }*/
    //Optional<List<Exercise>> findByUsedCourses_Course_Id(Long id);




}
