package ru.croc.coder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.domain.support.ExToFile;
import ru.croc.coder.repository.*;

import ru.croc.coder.domain.Code;
import ru.croc.coder.school.exercises.DifficultyLevelOfExercise;
import ru.croc.coder.school.exercises.ProgrammingLanguage;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.ExerciseConstrainException;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.NotPassedRestrictionsException;
import ru.croc.coder.service.exceptions.ServiceException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ExerciseService implements ServiceCommander {
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



    @Transactional(noRollbackFor = IOException.class)
    public Exercise exerciseFromFile(String fileName) throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            Resource resource = new ClassPathResource(fileName);
            File file = resource.getFile();

            ExToFile exToFile = objectMapper.readValue(file, ExToFile.class);

            return commandToAddExercise(exToFile.getDescription(),
                    exToFile.getIntLevel(),
                    exToFile.getIntLanguage(),
                    exToFile.getTemplateText(),
                    exToFile.getMaxAttempts());
        //List<Car> listCar = objectMapper.readValue(jsonCarArray, new  TypeReference<List<Car>>(){});
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Exercise> exercisesFromFile(String fileName){

        checkUserIsTeacher();

        ObjectMapper objectMapper = new ObjectMapper();
        Resource resource = new ClassPathResource(fileName);
        List<Exercise> exercises = null;
        try {
            File file = resource.getFile();
            ExToFile[] exToFiles = objectMapper.readValue(file, ExToFile[].class);
            exercises = new ArrayList<>();

            for(ExToFile exToFile : exToFiles) exercises.add(commandToAddExercise(
                    exToFile.getDescription(),
                    exToFile.getIntLevel(),
                    exToFile.getIntLanguage(),
                    exToFile.getTemplateText(),
                    exToFile.getMaxAttempts()));

        }
        catch (IOException e) {
            log.info(e.getMessage());
        }
      
        /*String jsonArray = objectMapper.readValue(file, String.class);
        System.out.println(jsonArray);
        List<ExToFile> exToFiles = objectMapper.readValue(jsonArray, new
                TypeReference<List<ExToFile>>(){});*/


        //List<ExToFile> exToFiles = objectMapper.readValue(file, new TypeReference<List<ExToFile>>(){});

        return exercises;
        //List<Car> listCar = objectMapper.readValue(jsonCarArray, new  TypeReference<List<Car>>(){});
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ServiceException.class)
    public Exercise commandToAddExercise (String description,
                                    Integer intLevel,
                                    Integer intLanguage,
                                    String templateText,
                                    Integer maxAttempts) {

        checkUserIsTeacher();

        if (intLanguage<0 || intLanguage>=programmingLanguages.length)
            throw new ExerciseConstrainException("Wrong format");
        ProgrammingLanguage language = programmingLanguages[intLanguage];

        if (intLevel<0 || intLevel>=difficultyLevels.length)
            throw new ExerciseConstrainException("Wrong format");
        DifficultyLevelOfExercise level = difficultyLevels[intLevel];

        return addExercise (userContext.getCurrentUser(), description, level, language, templateText, maxAttempts);
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
    public void checkUserIsTeacher() {
        checkUserIsTeacher(userContext.getCurrentUser());
    }

}
