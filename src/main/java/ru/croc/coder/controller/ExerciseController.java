package ru.croc.coder.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.croc.coder.controller.dto.ExerciseDto;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.CourseService;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.UserContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExerciseController {
    private static final Logger log = LoggerFactory.getLogger(ExerciseController.class);
    private ExerciseService exerciseService;
    private UserRepository userRepository;
    private UserContext userContext;
    private CourseService courseService;
    private ModelMapper modelMapper = new ModelMapper();

    public ExerciseController(ExerciseService exerciseService,
                              UserRepository userRepository,
                              UserContext userContext,
                              CourseService courseService /*,
                              ModelMapper modelMapper*/) {
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
        this.userContext = userContext;
        this.courseService = courseService;
        //this.modelMapper = modelMapper;
    }

/*
    String description,
    DifficultyLevelOfExercise level,
    ProgrammingLanguage language,
    String templateText,
    Integer maxAttempts)*/


    //DiffLev: 0 - Easy, 1 - Average, 2 - Hard, other - error
    //ProgLang: 0 - Java, other - error
    @PostMapping("/exercises/create/{description}/{intDiffLev}/{intProgLang}/{maxAttempts}")
    public ExerciseDto createExercise (@PathVariable String description,
                                       @PathVariable Integer intDiffLev,
                                       @PathVariable Integer intProgLang,
                                       @PathVariable Integer maxAttempts,
                                       @RequestBody String templateText){
      Exercise exercise = exerciseService.commandToAddExercise(
              description,
              intDiffLev,
              intProgLang,
              templateText,
              maxAttempts);
      ExerciseDto exerciseDto = modelMapper.map(exercise, ExerciseDto.class);
      //Shame разобраться с addMappings
      exerciseDto.setCodeProgrammingLanguage(exercise.getTemplate().getProgrammingLanguage());
      return exerciseDto;
    }

    @GetMapping("/exlist/{courseId}")
    public List<ExerciseDto> getExCourse(@PathVariable Long courseId) {
        List<Exercise> exercises = courseService.CommandGetExercisesCourse(courseId);
        List<ExerciseDto> exerciseDtos = new ArrayList<>();
        //SHAME SHAME SHAME
        for (Exercise exercise: exercises) {
            ExerciseDto exerciseDto = modelMapper.map(exercise, ExerciseDto.class);
            exerciseDto.setCodeProgrammingLanguage(exercise.getTemplate().getProgrammingLanguage());
            exerciseDtos.add(exerciseDto);
        }
        return exerciseDtos;
    }
}
