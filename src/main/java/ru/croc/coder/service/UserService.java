package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.User;
import ru.croc.coder.domain.UserStat;
import ru.croc.coder.repository.*;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.UserConstrainException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements ServiceCommander {

    public static final String PATTERN_NAME = "^[A-Za-z0-9-_]{3,16}$";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_PASSWORD = "^[A-Za-z0-9-_@#$%]{3,16}$";

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserCourseRegistrationRepository userCourseRegistrationRepository;
    private ExerciseRepository exerciseRepository;
    private CourseRepository courseRepository;
    private UserContext userContext;
    private ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository;
    private UserRepository userRepository;

    public UserService(UserCourseRegistrationRepository userCourseRegistrationRepository,
                         ExerciseRepository exerciseRepository,
                         CourseRepository courseRepository,
                         UserContext userContext,
                         ExerciseCourseRegistrationRepository exerciseCourseRegistrationRepository,
                         UserRepository userRepository) {
        this.userCourseRegistrationRepository = userCourseRegistrationRepository;
        this.exerciseRepository = exerciseRepository;
        this.courseRepository = courseRepository;
        this.userContext = userContext;
        this.exerciseCourseRegistrationRepository = exerciseCourseRegistrationRepository;
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = UserConstrainException.class)
    public User authorization(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        checkUserPassword(user, password);
        userContext.setCurrentUser(user);
        return user;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = UserConstrainException.class)
    public User authorization(String email, String password) {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(NotFoundException::new);
        checkUserPassword(user, password);
        userContext.setCurrentUser(user);
        return user;
    }

    public void checkUserPassword(User user, String password) {
        if (!convertToSecure(password).equals(user.getPassword()))
            throw new UserConstrainException("Invalidate password");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = UserConstrainException.class)
    public User addUser(String firstName, String lastName, String email, String password) {
       checkAllowableName(firstName);
       checkAllowableName(lastName);
       checkAllowableEmail(email);
       checkAllowablePassword(password);

       User user = new User()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(convertToSecure(password))
                .setSchoolRank(SchoolRank.STUDENT);
       Long userId = userRepository.save(user).getId();
       log.info("Created user id: {}", userId);
       return user;
    }


    public User commandSetUserToTeacher(Long userId) {
        if (!checkUserIsTeacher(userContext.getCurrentUser()))
            throw new UserConstrainException("Teacher must authorized to convert user to Teacher");
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return setUserToTeacher(user);
    }

    public User setUserToTeacher(User user) {
        user.setSchoolRank(SchoolRank.TEACHER);
        userRepository.save(user);
        return user;
    }

    public void checkAllowableName(String name) {
        Pattern pattern = Pattern.compile(PATTERN_NAME);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) throw new UserConstrainException("Invalidate name");
    }
    public void checkAllowableEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new UserConstrainException("Invalidate email");

        if (userRepository.findByEmailIgnoreCase(email).isPresent())
            throw new UserConstrainException("This email already exists");
    }
    public void  checkAllowablePassword(String pass) {
        Pattern pattern = Pattern.compile(PATTERN_PASSWORD);
        Matcher matcher = pattern.matcher(pass);
        if (!matcher.matches()) throw new UserConstrainException("Invalidate password");
    }

    public UserStat buildUserStat(Long userId) {
        userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return new UserStat().
                setUserId(userId).
                setEasyDecidedExercisesN(exerciseRepository.userStat(userId, DifficultyLevelOfExercise.EASY)).
                setAverageDecidedExercisesN(exerciseRepository.userStat(userId, DifficultyLevelOfExercise.AVERAGE)).
                setHardDecidedExercisesN(exerciseRepository.userStat(userId, DifficultyLevelOfExercise.HARD));
    }


}
