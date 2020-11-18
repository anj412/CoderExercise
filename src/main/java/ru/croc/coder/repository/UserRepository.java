package ru.croc.coder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.lang.NonNull;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.pearsons.SchoolRank;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmailIgnoreCase(String email);
	//Optional<User> findById(Long id);


/*	@Query("Select u from User u where u.SchoolRank = ?1")
	List<User> findBySchoolStatus(SchoolRank schoolRank);*/

	List<User> findBySchoolRankEquals(SchoolRank schoolRank);

	List<User> findBySchoolRankAndRegistrations_Course(SchoolRank schoolRank, Course course);

	long countBySchoolRankAndRegistrations_Course_Id(SchoolRank schoolRank, Long id);








}
