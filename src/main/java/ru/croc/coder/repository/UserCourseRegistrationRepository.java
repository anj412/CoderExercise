package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.domain.UserCourseRegistration;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.pearsons.SchoolRank;

import java.util.List;
import java.util.Set;

public interface UserCourseRegistrationRepository extends CrudRepository<UserCourseRegistration, Long> {

    long countByCourseAndUser (Course course, User user);
}
