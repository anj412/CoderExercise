package ru.croc.coder.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.lang.NonNull;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.pearsons.SchoolRank;

public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findTopByRegistrations_Course_IdNot(Long id);

	Optional<User> findTopByRegistrations_Course_Id(Long id);




	Optional<User> findByEmailIgnoreCase(String email);
	//Optional<User> findById(Long id);


/*	@Query("Select u.user from Users u")
	List<User> selectAllUsers();*/

	List<User> findAll();


	long countBySchoolRankAndRegistrations_Course_Id(SchoolRank schoolRank, Long id);


	Set<User> findByRegistrations_Course_IdAndSolutions_PassedAndSolutions_ExerciseAndSchoolRank
			(Long id, Boolean passed, Exercise exercise, SchoolRank schoolRank);
	default Long countExerciseCourseStat(Long courseId, Exercise exercise) {
		return (long)findByRegistrations_Course_IdAndSolutions_PassedAndSolutions_ExerciseAndSchoolRank
				(courseId, true, exercise, SchoolRank.STUDENT).size();
	}









}
