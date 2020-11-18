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
import ru.croc.coder.service.CourseService;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.UserService;

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
	private CourseService courseService;
	private ExerciseService exerciseService;
	private UserService userService;

	public Init(UserRepository userRepository,
				ExerciseRepository exerciseRepository,
				CourseRepository courseRepository,
				SolutionRepository solutionRepository,
				ExerciseAttachmentRepository exerciseAttachmentRepository,
				ExerciseRestrictionRepository exerciseRestrictionRepository,
				UserCourseRegistrationRepository userCourseRegistrationRepository,
				ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository,
				CourseService courseService,
				ExerciseService exerciseService,
				UserService userService) {
		this.userRepository = userRepository;
		this.exerciseRepository = exerciseRepository;
		this.courseRepository = courseRepository;
		this.solutionRepository = solutionRepository;
		this.exerciseAttachmentRepository = exerciseAttachmentRepository;
		this.exerciseRestrictionRepository = exerciseRestrictionRepository;
		this.userCourseRegistrationRepository = userCourseRegistrationRepository;
		this.exerciseCourseRegistrationRepository = exerciseCourseRegistrationRepository;
		this.courseService = courseService;
		this.exerciseService = exerciseService;
		this.userService = userService;
	}

	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		User user1 = userService.addUser("Evgeny", "Pisarenko", "episarenko@croc.ru", "123");
		User user2 = userService.addUser("Andrew", "Kostromin", "6620@croc.ru", "123");
		User user3 = userService.addUser("Peter", "Vasechkin", "pop@glas.net", "12345");
		User user4 = userService.addUser("Kolya", "Pugovkin", "pop1@glas.net", "12345");
		userService.setUserToTeacher(user1);

		User author = userRepository.findByEmailIgnoreCase("episarenko@croc.ru").get();
		Exercise exercise1 = exerciseService.addExercise(author, "First exercise", DifficultyLevelOfExercise.EASY,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise2= exerciseService.addExercise(author,"Second exercise", DifficultyLevelOfExercise.AVERAGE,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise3 = exerciseService.addExercise(author, "Third exercise", DifficultyLevelOfExercise.HARD,
				ProgrammingLanguage.JAVA, "a+b", 3);
		Exercise exercise4 = exerciseService. addExercise(author, "Fouth exercise",DifficultyLevelOfExercise.AVERAGE,
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
		
		

		Course course1 = courseService.addCourse("Начальный",CourseStatus.OPENED);
		Course course2 = courseService.addCourse("Особый",CourseStatus.CLOSED);
		courseService.addCourse("Средний",CourseStatus.OPENED);


		addSimpleRestriction(exercise1, LocalDateTime.of(2020,12, 31, 8, 00),
				LocalDateTime.of(2025, 1, 1, 19, 59));
		addSimpleRestriction(exercise2, LocalDateTime.of(2015,12, 31, 8, 00),
				LocalDateTime.of(2025, 1, 1, 19, 59));
		addSimpleRestriction(exercise3, null,
				LocalDateTime.of(2025, 1, 1, 19, 59));

		UserCourseRegistration userCourseRegistration1 = courseService.addUserCourseRegistration(user2, course1);
		UserCourseRegistration userCourseRegistration2 = courseService.addUserCourseRegistration(user3, course1);
		//UserCourseRegistration userCourseRegistrationNull = courseService.addUserCourseRegistration(user3, course1);
		UserCourseRegistration userCourseRegistration3 = courseService.addUserCourseRegistration(user4, course2);
		UserCourseRegistration userCourseRegistration4 = courseService.addUserCourseRegistration(user1, course1);

		ExerciseCourseRegistration exerciseCourseReg1 = courseService.addExerciseCourseRegistration(exercise1, course1);
		ExerciseCourseRegistration exerciseCourseReg2 = courseService.addExerciseCourseRegistration(exercise2, course1);
		ExerciseCourseRegistration exerciseCourseReg3 = courseService.addExerciseCourseRegistration(exercise3, course2);
		//ExerciseCourseRegistration exerciseCourseRegN = courseService.addExerciseCourseRegistration(exercise3, course2);
		//System.out.println(courseService.bruteFindExercisesByCourse(course1));
		//System.out.println(course1.getExerciseRegistrations());


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


	
	public ExerciseAttachment addExerciseAttachment (String description, Exercise exercise) {
		ExerciseAttachment exerciseAttachment = new ExerciseAttachment()
				.setDescription(description)
				.setExercise(exercise);
		Long exerciseAttachmentId = exerciseAttachmentRepository.save(exerciseAttachment).getId();
		log.info("Created attachment id: {}", exerciseAttachmentId);
		return exerciseAttachment;		
	}

}
