package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.ExerciseCourseRegistration;
import ru.croc.coder.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExerciseCourseRegistrationRepository extends CrudRepository<ExerciseCourseRegistration, Long> {

    long countByExerciseAndCourse (Exercise exercise, Course course);

    @Query("select e.exercise from ExerciseCourseRegistration e where e.course = ?1")
    public List<Exercise> findExercisesByCourse(Course course);






}
