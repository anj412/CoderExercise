package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.domain.support.CourseStat;
import ru.croc.coder.repository.*;
import ru.croc.coder.school.courses.CourseStatus;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.CourseConstrainException;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import ru.croc.coder.service.exceptions.NotFoundException;

import java.util.*;

@Service
public class CourseService implements ServiceCommander {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    private static final CourseStatus courseStatuses[] = CourseStatus.values();

    private UserCourseRegistrationRepository userCourseRegistrationRepository;
    private ExerciseRepository exerciseRepository;
    private CourseRepository courseRepository;
    private UserContext userContext;
    private ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository;
    private UserRepository userRepository;
    private SolutionRepository solutionRepository;

    public CourseService(UserCourseRegistrationRepository userCourseRegistrationRepository,
                         ExerciseRepository exerciseRepository,
                         CourseRepository courseRepository,
                         UserContext userContext,
                         ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository,
                         UserRepository userRepository,
                         SolutionRepository solutionRepository) {
        this.userCourseRegistrationRepository = userCourseRegistrationRepository;
        this.exerciseRepository = exerciseRepository;
        this.courseRepository = courseRepository;
        this.userContext = userContext;
        this.exerciseCourseRegistrationRepository = exerciseCourseRegistrationRepository;
        this.userRepository = userRepository;
        this.solutionRepository = solutionRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = CourseConstrainException.class)
    public Course commandToAddCourse(Integer intCourseType, String description) {

        if (!checkUserIsTeacher(userContext.getCurrentUser()))
            throw new CourseConstrainException("Teacher must authorized to create new course");

        if (intCourseType<0 || intCourseType>= courseStatuses.length)
            throw new ExerciseConstrainException("Wrong format");
        CourseStatus courseStatus = courseStatuses[intCourseType];

        return addCourse(description, courseStatus);
    }

    public Course addCourse (String description, CourseStatus courseStatus) {
        Course course = new Course()
                .setDescription(description)
                .setCourseStatus(courseStatus);
        Long courseId = courseRepository.save(course).getId();
        log.info("Created course id: {}", courseId);
        return course;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = CourseConstrainException.class)
    public ExerciseCourseRegistration commandToRegExercise(Long exerciseId, Long courseId) {
        if (!checkUserIsTeacher(userContext.getCurrentUser()))
            throw new CourseConstrainException("Teacher must authorized to create new course");

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);

        return addExerciseCourseRegistration(exercise, course);
    }
    public ExerciseCourseRegistration addExerciseCourseRegistration(Exercise exercise, Course course) {
        if (exerciseCourseRegistrationRepository.countByExerciseAndCourse(exercise, course) > 0) {
            log.info("Wrong ExerciseRegistration: exercise_id {} is already registered at course_id {}",
                    exercise.getId(), course.getId());

        }
        ExerciseCourseRegistration registration = new ExerciseCourseRegistration().
                setExercise(exercise).
                setCourse(course);
        Long id = exerciseCourseRegistrationRepository.save(registration).getId();
        log.info("Created exerciseCourseRegistration id: {}", id);
        return registration;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = CourseConstrainException.class)
    public UserCourseRegistration commandToRegUser(Long userId, Long courseId) {
        if (!checkUserIsTeacher(userContext.getCurrentUser()))
            throw new CourseConstrainException("Teacher must authorized to create new course");

        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);

        return addUserCourseRegistration(user, course);
    }
    public  UserCourseRegistration commandToAddUserCourseRegistration (Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return addUserCourseRegistration(user, course);
    }


    public UserCourseRegistration addUserCourseRegistration(User user, Course course) {
        if (userCourseRegistrationRepository.countByCourseAndUser(course, user) > 0) {
            log.info("Wrong UserCourseRegistration: user_id {} is already registered at course_id {}",
                    user.getId(), course.getId());
            throw new CourseConstrainException("Wrong UserCourseRegistration: user_id " + user.getId() +
                    " is already registered at course_id " + course.getId());
        }
        UserCourseRegistration userCourseRegistration = new UserCourseRegistration().
                setCourse(course).
                setUser(user);
        Long id = userCourseRegistrationRepository.save(userCourseRegistration).getId();
        log.info("Created courseRegistration id: {}", id);
        return userCourseRegistration;
    }

    public List<Exercise> CommandGetExercisesCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        return exerciseCourseRegistrationRepository.findExercisesByCourse(course);

        //return exerciseRepository.findByUsedCourses_Course_Id(courseId).orElseThrow(NotFoundException::new);
    }

    public CourseStat courseStat(Long courseId) {

        if (!checkUserIsTeacher(userContext.getCurrentUser()))
            throw new CourseConstrainException("Teacher must authorized to get this stat");
        List<Exercise> exercises = CommandGetExercisesCourse(courseId);

        Map<Long,Long> exStat = new HashMap<>();
        for (Exercise exercise:exercises)
            exStat.put(exercise.getId(), userRepository.countExerciseCourseStat(courseId, exercise));

        return new CourseStat().
                setCourseId(courseId).
                setStudentsNumber(userRepository.countBySchoolRankAndRegistrations_Course_Id(SchoolRank.STUDENT, courseId)).
                setTeachersNumber(userRepository.countBySchoolRankAndRegistrations_Course_Id(SchoolRank.TEACHER, courseId)).
                setExStat(exStat);
    }



}
