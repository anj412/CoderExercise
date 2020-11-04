package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.UserCourseRegistration;
import ru.croc.coder.domain.User;

public interface UserCourseRegistrationRepository extends CrudRepository<UserCourseRegistration, Long> {

    long countByCourseAndUser (Course course, User user);
}
