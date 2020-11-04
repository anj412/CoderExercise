package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.ExerciseCourseRegistration;

public interface ExerciseCourseRegistrationRepository extends CrudRepository<ExerciseCourseRegistration, Long> {

    long countByExerciseAndCourse (Exercise exercise, Course course);
}
