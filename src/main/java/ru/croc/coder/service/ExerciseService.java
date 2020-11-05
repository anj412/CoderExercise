package ru.croc.coder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.ExerciseRestrictionRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;

import ru.croc.coder.domain.Code;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.NotPassedRestrictionsException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ExerciseService {

    private static final Random rnd = new Random(System.currentTimeMillis());


    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private SolutionRepository solutionRepository;
    private ExerciseRestrictionRepository exerciseRestrictionRepository;
    private UserContext userContext;

    public ExerciseService(UserRepository userRepository,
                           ExerciseRepository exerciseRepository,
                           SolutionRepository solutionRepository,
                           ExerciseRestrictionRepository exerciseRestrictionRepository,
                           UserContext userContext) {
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
        this.solutionRepository = solutionRepository;
        this.exerciseRestrictionRepository = exerciseRestrictionRepository;
        this.userContext = userContext;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ExerciseConstrainException.class)
    public Solution submit (Long exerciseId, String text) {

        //User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);

        User user = userContext.getCurrentUser();

        userRepository.save(user.setAttemptsCount(user.getAttemptsCount()+1));

        if (exercise.getMaxAttempts() != null) {
            long attempts = solutionRepository.countByAuthorAndExercise(user, exercise);
            if (attempts >= exercise.getMaxAttempts())
                throw new ExerciseConstrainException("Max attempts exceeded");
        }

        //SHAME

        if (!restrictionCheck(exercise))
            throw new NotPassedRestrictionsException("General not pass restriction");

        Solution solution = new Solution()
                .setAuthor(user)
                .setExercise(exercise)
                .setTime(LocalDateTime.now())
                .setCode(new Code().setText(text).setLanguage(exercise.getTemplate().getLanguage()))
                .setPassed(rnd.nextBoolean());
        return solutionRepository.save(solution);
    }

    public boolean restrictionCheck(Exercise exercise) {
        ExerciseRestriction restriction = exercise.getRestriction();
        //SHAME ?
        if (restriction == null) return true;
        //SHAME SHAME
        if (restriction.getTimeOpened() != null && LocalDateTime.now().isBefore(restriction.getTimeOpened()))
            throw new NotPassedRestrictionsException("Its too early");
        //SHAME SHAME
        if (restriction.getTimeClosed() != null && LocalDateTime.now().isAfter(restriction.getTimeClosed()))
            throw new NotPassedRestrictionsException("Its too late. ");

        //check duration .. str += "Its too lengthy. "
        //check RAM .. str += "Its too hungry. "
        //check Disk Storage .. str += "Its too big."

        return true;
    }
}
