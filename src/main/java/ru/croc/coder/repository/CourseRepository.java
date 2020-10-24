package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
