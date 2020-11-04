package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.*;
import ru.croc.coder.repository.*;
import ru.croc.coder.school.courses.CourseStatus;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.ProgrammingLanguage;
import ru.croc.coder.school.pearsons.SchoolRank;

import java.time.LocalDateTime;

@Component
public class Init implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Init.class);


	private UserRepository userRepository;
	private ExerciseRepository exerciseRepository;
	private CourseRepository courseRepository;
	private SolutionRepository solutionRepository;
	private ExerciseAttachmentRepository exerciseAttachmentRepository;
	private ExerciseRestrictionRepository exerciseRestrictionRepository;
	private UserCourseRegistrationRepository userCourseRegistrationRepository;
	private ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository;

	public Init(UserRepository userRepository,
				ExerciseRepository exerciseRepository,
				CourseRepository courseRepository,
				SolutionRepository solutionRepository,
				ExerciseAttachmentRepository exerciseAttachmentRepository,
				ExerciseRestrictionRepository exerciseRestrictionRepository,
				UserCourseRegistrationRepository userCourseRegistrationRepository,
				ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository) {
		this.userRepository = userRepository;
		this.exerciseRepository = exerciseRepository;
		this.courseRepository = courseRepository;
		this.solutionRepository = solutionRepository;
		this.exerciseAttachmentRepository = exerciseAttachmentRepository;
		this.exerciseRestrictionRepository = exerciseRestrictionRepository;
		this.userCourseRegistrationRepository = userCourseRegistrationRepository;
		this.exerciseCourseRegistrationRepository = exerciseCourseRegistrationRepository;
	}

	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		User user1 = addUser("Evgeny", "Pisarenko", "episarenko@croc.ru", "");
		User user2 = addUser("Andrew", "Kostromin", "6620@croc.ru", "");
		User user3 = addUser("Peter", "Vasechkin", "pop@glas.net", "");
		User user4 = addUser("Kolya", "Pugovkin", "pop1@glas.net", "");
		user1.setSchoolRank(SchoolRank.TEACHER);
		userRepository.save(user1);

		User author = userRepository.findByEmailIgnoreCase("episarenko@croc.ru").get();
		Exercise exercise1 = addExercise(author, "First exercise", DifficultyLevelOfExercise.EASY,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise2= addExercise(author,"Second exercise", DifficultyLevelOfExercise.AVERAGE,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise3 = addExercise(author, "Third exercise", DifficultyLevelOfExercise.HARD,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise4 = addExercise(author, "Fouth exercise",DifficultyLevelOfExercise.AVERAGE,
				ProgrammingLanguage.JAVA, "a+b", 3);
		//addExercise(author, "5th exercise",DifficultyLevelOfExercise.AVERAGE, ProgrammingLanguage.JAVA, "a+b", 3);
		//addExercise(author, "6th exercise",DifficultyLevelOfExercise.AVERAGE, ProgrammingLanguage.JAVA, "a+b", 3);
		//addExercise(author, "7th exercise",DifficultyLevelOfExercise.AVERAGE, ProgrammingLanguage.JAVA, "a+b", 3);
		//addExercise(author, "8th exercise",DifficultyLevelOfExercise.AVERAGE, ProgrammingLanguage.JAVA, "a+b", 3);
		//addExercise(author, "9th exercise",DifficultyLevelOfExercise.AVERAGE, ProgrammingLanguage.JAVA, "a+b", 3);
		
		addExerciseAttachment("1th for Second", exercise2);
		addExerciseAttachment("2th for Second", exercise2);
		addExerciseAttachment("3th for Second", exercise2);
		addExerciseAttachment("1th for Third", exercise3);
		addExerciseAttachment("2th for Third", exercise3);
		
		

		Course course1 = addCourse("Начальный",CourseStatus.OPENED);
		Course course2 = addCourse("Особый",CourseStatus.CLOSED);
		addCourse("Средний",CourseStatus.OPENED);


		addSimpleRestriction(exercise1, LocalDateTime.of(2020,12, 31, 8, 00),
				LocalDateTime.of(2025, 1, 1, 19, 59));
		addSimpleRestriction(exercise2, LocalDateTime.of(2015,12, 31, 8, 00),
				LocalDateTime.of(2025, 1, 1, 19, 59));
		addSimpleRestriction(exercise3, null,
				LocalDateTime.of(2025, 1, 1, 19, 59));

		UserCourseRegistration userCourseRegistration1 = addUserCourseRegistration(user2, course1);
		UserCourseRegistration userCourseRegistration2 = addUserCourseRegistration(user3, course1);
		UserCourseRegistration userCourseRegistrationNull = addUserCourseRegistration(user3, course1);
		UserCourseRegistration userCourseRegistration3 = addUserCourseRegistration(user4, course2);

		ExerciseCourseRegistration exerciseCourseRegistration1 = addExerciseCourseRegistration(exercise1, course1);
		ExerciseCourseRegistration exerciseCourseRegistration2 = addExerciseCourseRegistration(exercise2, course1);
		ExerciseCourseRegistration exerciseCourseRegistration3 = addExerciseCourseRegistration(exercise3, course2);
		ExerciseCourseRegistration exerciseCourseRegistrationN = addExerciseCourseRegistration(exercise3, course2);
	}

	public ExerciseCourseRegistration addExerciseCourseRegistration(Exercise exercise, Course course) {
		if (exerciseCourseRegistrationRepository.countByExerciseAndCourse(exercise, course) > 0) {
			log.info("Wrong ExerciseRegistration: exercise_id {} is already registered at course_id {}",
					exercise.getId(), course.getId());
			return null;
		}
		ExerciseCourseRegistration registration = new ExerciseCourseRegistration().
				setExercise(exercise).
				setCourse(course);
		Long id = exerciseCourseRegistrationRepository.save(registration).getId();
		log.info("Created exerciseCourseRegistration id: {}", id);
		return registration;
	}

	public UserCourseRegistration addUserCourseRegistration(User student, Course course) {
		if (userCourseRegistrationRepository.countByCourseAndUser(course, student) > 0) {
			log.info("Wrong UserCourseRegistration: student_id {} is already registered at course_id {}",
					student.getId(), course.getId());
			return null;
		}
		UserCourseRegistration userCourseRegistration = new UserCourseRegistration().
				setCourse(course).
				setUser(student);
		Long id = userCourseRegistrationRepository.save(userCourseRegistration).getId();
		log.info("Created courseRegistration id: {}", id);
		return userCourseRegistration;
	}

	public ExerciseRestriction addSimpleRestriction (Exercise exercise,
													 LocalDateTime timeOpened, LocalDateTime timeClosed) {
		ExerciseRestriction restriction = new ExerciseRestriction()
				.setTimeOpened(timeOpened)
				.setTimeClosed(timeClosed);
		Long restrictionId = exerciseRestrictionRepository.save(restriction).getId();
		log.info("Created restriction id: {}", restrictionId);
		exerciseRepository.save(exercise.setRestriction(restriction));
		return restriction;
	}




	public Course addCourse (String description, CourseStatus courseStatus) {
		Course course = new Course()
				.setDescription(description)
				.setCourseStatus(courseStatus);
		Long courseId = courseRepository.save(course).getId();
		log.info("Created course id: {}", courseId);
		return course;
	}


	public Exercise addExercise  (User author, String description, DifficultyLevelOfExercise level,
								  ProgrammingLanguage language, String text, Integer maxAttempts) {
		Exercise exercise = new Exercise()
				.setAuthor(author)
				.setDescription(description)
				.setDifficultyLevel(level)
				.setTemplate(new Code().setText(text).setLanguage(language))
				.setMaxAttempts(maxAttempts);
		Long exerciseId = exerciseRepository.save(exercise).getId();
		log.info("Created exercise id: {}", exerciseId);
		return exercise;
	}

	public User addUser(String firstName, String lastName, String email, String password) {
		User user = null;
		if (userRepository.findByEmailIgnoreCase(email).isEmpty()) {
			user = new User()
					.setFirstName(firstName)
					.setLastName(lastName)
					.setEmail(email)
					.setPassword(password);
			Long userId = userRepository.save(user).getId();
			log.info("Created user id: {}", userId);
		}
		return user;
	}
	
	public ExerciseAttachment addExerciseAttachment (String description, Exercise exercise) {
		ExerciseAttachment exerciseAttachment = new ExerciseAttachment()
				.setDescription(description)
				.setExercise(exercise);
		Long exerciseAttachmentId = exerciseAttachmentRepository.save(exerciseAttachment).getId();
		log.info("Created attachment id: {}", exerciseAttachmentId);
		return exerciseAttachment;		
	}

/*	private Exercise createExercise (User author, String description, DifficultyLevelOfExercise level,
								 ProgrammingLanguage language, String text, Integer maxAttempts) {
		Exercise exercise = new Exercise()
				.setAuthor(author)
				.setDescription(description)
				.setDifficultyLevel(level)
				.setTemplate(new Code().setText(text).setLanguage(language))
				.setMaxAttempts(maxAttempts);
		Long exerciseId = exerciseRepository.save(exercise).getId();
		log.info("Created exercise id: {}", exerciseId);
		return exercise;
	}*/
}
