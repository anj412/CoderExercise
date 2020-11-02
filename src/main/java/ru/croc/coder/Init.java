package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.*;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.ExerciseAttachmentRepository;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.school.courses.CourseStatus;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.ProgrammingLanguage;
import ru.croc.coder.school.pearsons.SchoolRank;

@Component
public class Init implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Init.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExerciseRepository exerciseRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private SolutionRepository solutionRepository;
	@Autowired
	private ExerciseAttachmentRepository exerciseAttachmentRepository;



	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		User user1 = addUser("Evgeny", "Pisarenko", "episarenko@croc.ru", "");
		User user2 = addUser("Andrew", "Kostromin", "6620@croc.ru", "");
		User user3 = addUser("Peter", "Vasechkin", "pop@glas.net", "");
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
		
		

		addCourse("Начальный",CourseStatus.OPENED);
		addCourse("Особый",CourseStatus.CLOSED);
		addCourse("Средний",CourseStatus.OPENED);




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
		log.info("Created user id: {}", exerciseAttachmentId);
		return exerciseAttachment;		
	}

	private Exercise createExercise (User author, String description, DifficultyLevelOfExercise level,
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
}
