package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Course;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<Course> findFirstByOrderByIdAsc();

}
