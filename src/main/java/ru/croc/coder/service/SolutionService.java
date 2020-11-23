package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.ExerciseRestrictionRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.NotPassedRestrictionsException;
import ru.croc.coder.service.exceptions.ServiceException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class SolutionService {
    private static final Logger log = LoggerFactory.getLogger(SolutionService.class);
    private static final Random rnd = new Random(System.currentTimeMillis());

    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private SolutionRepository solutionRepository;
    private ExerciseRestrictionRepository exerciseRestrictionRepository;
    private UserContext userContext;


    public SolutionService(UserRepository userRepository,
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

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ServiceException.class)
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

        //restrictionCheck(exercise);

        Solution solution = new Solution()
                .setAuthor(user)
                .setExercise(exercise)
                .setTime(LocalDateTime.now())
                .setCode(new Code().setCodeText(text).setProgrammingLanguage(exercise.getTemplate().getProgrammingLanguage()))
                .setCheckStatus(ProcessStatus.QUEUED);

        solutionRepository.save(solution);
        log.info("Created solution id: {}", solution.getId());
        return solution;
    }



    @Async //чтобы позволить запускать методы раньше завершения
    @Scheduled(fixedRate = 1_000, initialDelay = 3_000) //будет вызывает метод с какой то переодичностью. 1) с фикс частотой 2) запуск с задержкой
    public void checkSolutions() throws InterruptedException {
        log.info("Scheduled check");
        Solution solution = null;
        Boolean passed = null;
        try {
            Optional<Solution> result = peekNextSolution();
            if (result.isEmpty()) return;
            solution = result.get();
            passed = runTests(solution);
            solution.setPassed(passed);
        } finally {
            if (solution != null ) {
                ProcessStatus status;
                if (passed != null) status = ProcessStatus.COMPLETED;
                else status = ProcessStatus.COMPLETED_WITH_ERROR;
                solutionRepository.save(solution.setCheckStatus(status));
            }
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<Solution> peekNextSolution() {
        Optional<Solution> result = solutionRepository.findAnyQueued();
        if (result.isPresent()) {
            Solution solution = result.get();
            solution.setCheckStatus(ProcessStatus.IN_PROGRESS);
            solutionRepository.save(solution);
        }
        return result;
    }

    private boolean runTests(Solution solution) throws InterruptedException {
        Thread.sleep(15000);
        return rnd.nextBoolean();
    }

    @Transactional(noRollbackFor = NotPassedRestrictionsException.class)
    public boolean restrictionCheck(Exercise exercise) {
        ExerciseRestriction restriction = exercise.getRestriction();
        //SHAME ?
        if (restriction == null) {
            log.info("There is no restriction to this exercise id {} Ok. Creating solution.. ", exercise.getId());
            return true;
        }
        //SHAME SHAME
        if (restriction.getTimeOpened() != null && LocalDateTime.now().isBefore(restriction.getTimeOpened()))
            throw new NotPassedRestrictionsException("Its too early");
        //SHAME SHAME
        if (restriction.getTimeClosed() != null && LocalDateTime.now().isAfter(restriction.getTimeClosed()))
            throw new NotPassedRestrictionsException("Its too late. ");

        //TODO
        //check duration
        //check RAM
        //check Disk Storage

        log.info("All restrictions to this exercise id {} Ok. Creating solution.. ",
                exercise.getId());
        return true;
    }
}
