package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.*;

import ru.croc.coder.domain.Code;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.ProgrammingLanguage;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.NotPassedRestrictionsException;
import ru.croc.coder.service.exceptions.ServiceException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class ExerciseService {

    private static final Random rnd = new Random(System.currentTimeMillis());

    private static final ProgrammingLanguage programmingLanguages[] = ProgrammingLanguage.values();
    private static final DifficultyLevelOfExercise difficultyLevels[] = DifficultyLevelOfExercise.values();

    private static final Logger log = LoggerFactory.getLogger(ExerciseService.class);

    private UserRepository userRepository;
    private ExerciseRepository exerciseRepository;
    private SolutionRepository solutionRepository;
    private ExerciseRestrictionRepository exerciseRestrictionRepository;
    private UserContext userContext;


    public ExerciseService(UserRepository userRepository,
                           ExerciseRepository exerciseRepository,
                           SolutionRepository solutionRepository,
                           ExerciseRestrictionRepository exerciseRestrictionRepository,
                           UserContext userContext
                           ) {
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
    @Scheduled(fixedRate = 3_000, initialDelay = 3_000) //будет вызывает метод с какой то переодичностью. 1) с фикс частотой 2) запуск с задержкой
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


    public boolean restrictionCheck(Exercise exercise) {
        ExerciseRestriction restriction = exercise.getRestriction();
        //SHAME ?
        if (restriction == null) {
            log.info("There is no restriction to this exercise id {} Ok. Creating solution.. ",
                    exercise.getId());
            return true;
        }
        //SHAME SHAME
        if (restriction.getTimeOpened() != null && LocalDateTime.now().isBefore(restriction.getTimeOpened()))
            throw new NotPassedRestrictionsException("Its too early");
        //SHAME SHAME
        if (restriction.getTimeClosed() != null && LocalDateTime.now().isAfter(restriction.getTimeClosed()))
            throw new NotPassedRestrictionsException("Its too late. ");

        //check duration .. str += "Its too lengthy. "
        //check RAM .. str += "Its too hungry. "
        //check Disk Storage .. str += "Its too big."

        log.info("All restrictions to this exercise id {} Ok. Creating solution.. ",
                exercise.getId());
        return true;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ExerciseConstrainException.class)
    public Exercise commandToAddExercise (String description,
                                    Integer intLevel,
                                    Integer intLanguage,
                                    String templateText,
                                    Integer maxAttempts) {

        User user = userContext.getCurrentUser();

        if (user == null && user.getSchoolRank() != SchoolRank.TEACHER)
            throw new ExerciseConstrainException("Teacher must authorized to create new exercise");

        if (intLanguage<0 || intLanguage>=programmingLanguages.length)
            throw new ExerciseConstrainException("Wrong format");
        ProgrammingLanguage language = programmingLanguages[intLanguage];

        if (intLevel<0 || intLevel>=difficultyLevels.length)
            throw new ExerciseConstrainException("Wrong format");
        DifficultyLevelOfExercise level = difficultyLevels[intLevel];

        return addExercise (user, description, level, language, templateText, maxAttempts);
    }

    public Exercise addExercise  (User author,
                                  String description,
                                  DifficultyLevelOfExercise level,
                                  ProgrammingLanguage language,
                                  String templateText,
                                  Integer maxAttempts) {
        Exercise exercise = new Exercise()
                .setAuthor(author)
                .setDescription(description)
                .setDifficultyLevel(level)
                .setTemplate(new Code().setCodeText(templateText).setProgrammingLanguage(language))
                .setMaxAttempts(maxAttempts);
        Long exerciseId = exerciseRepository.save(exercise).getId();
        log.info("Created exercise id: {}", exerciseId);
        return exercise;
    }


}
