package ru.croc.coder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.ExerciseRestrictionRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ExerciseService {

    private static final Random rnd = new Random(System.currentTimeMillis());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private ExerciseRestrictionRepository exerciseRestrictionRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Solution submit (Long userId, Long exerciseId, String text) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(NotFoundException::new);

        if (exercise.getMaxAttempts() != null) {
             long attempts = solutionRepository.countByAuthorAndExercise(user, exercise);
            if (attempts >= exercise.getMaxAttempts()) throw new ProblemConstrainException("Max attempts exceeded");
        }

        //SHAME
        if (! restrictionCheck(exercise)) throw new NotPassedRestrictionsException("General not pass");

        Solution solution = new Solution()
                .setAuthor(user)
                .setExercise(exercise)
                .setTime(LocalDateTime.now())
                .setCode(new Code().setText(text).setLanguage(exercise.getTemplate().getLanguage()))
                .setPassed(rnd.nextBoolean());
        return solutionRepository.save(solution);
    }

    public Boolean restrictionCheck(Exercise exercise) {
        ExerciseRestriction restriction = exercise.getRestriction();

        if (restriction == null) return true;

        //SHAME SHAME
        if (restriction.getTimeOpened() != null || LocalDateTime.now().isBefore(restriction.getTimeOpened()))
            throw new NotPassedRestrictionsException("Its too early");

        //SHAME SHAME
        if (restriction.getTimeClosed() != null || LocalDateTime.now().isAfter(restriction.getTimeClosed()))
            throw new NotPassedRestrictionsException("Its too late");

        return true;
    }
}
