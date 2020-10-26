package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.croc.coder.domain.User;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.domain.Course;
import ru.croc.coder.school.courses.CourseStatus;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.domain.Exercise;

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



	@Override
	public void run(String[] args) throws Exception {
		log.info("Init application");

		long numUser = userRepository.count();
		log.info("Number of users: {}", numUser);

		addUser("Evgeny", "Pisarenko", "episarenko@croc.ru", "");
		addUser("Andrew", "Kostromin", "6620@croc.ru", "");
		User teacher1 = addUser("Peter", "Vasechkin", "pop@glas.net", "");
		teacher1.setSchoolRank(SchoolRank.TEACHER);
		userRepository.save(teacher1);

		addExercise("First exercise", DifficultyLevelOfExercise.EASY);
		addExercise("Second exercise", DifficultyLevelOfExercise.AVERAGE);
		addExercise("Third exersise", DifficultyLevelOfExercise.HARD);
		addExercise("Fouth exercise",DifficultyLevelOfExercise.AVERAGE);
		addExercise("5th exercise",DifficultyLevelOfExercise.AVERAGE);
		addExercise("6th exercise",DifficultyLevelOfExercise.AVERAGE);
		addExercise("7th exercise",DifficultyLevelOfExercise.AVERAGE);
		addExercise("8th exercise",DifficultyLevelOfExercise.AVERAGE);
		//addExercise("9th exercise",DifficultyLevelOfExercise.AVERAGE);

		

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


	public Exercise addExercise (String description, DifficultyLevelOfExercise difficultyLevel) {
		Exercise exercise = new Exercise()
				.setDescription(description)
				.setDifficultyLevel(difficultyLevel);
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
}
