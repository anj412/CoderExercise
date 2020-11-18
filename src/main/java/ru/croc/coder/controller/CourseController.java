package ru.croc.coder.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.croc.coder.controller.dto.CourseDto;
import ru.croc.coder.controller.dto.CourseStatDto;
import ru.croc.coder.controller.dto.ExerciseDto;
import ru.croc.coder.domain.Course;
import ru.croc.coder.domain.Exercise;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.CourseService;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.UserContext;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    private ExerciseService exerciseService;
    private UserRepository userRepository;
    private UserContext userContext;
    private CourseService courseService;
    private ModelMapper modelMapper = new ModelMapper();

    public CourseController(ExerciseService exerciseService,
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
    //courseType: 0 - Opened, 1 - Closed, other - error
    @PostMapping("/courses/create/{courseType}")
    public CourseDto createCourse(@PathVariable Integer courseType, @RequestBody String text) {
        Course course = courseService.commandToAddCourse(courseType, text);
        return modelMapper.map(course, CourseDto.class);
    }

    @GetMapping("/courses/stat/{courseId}")
    public CourseStatDto courseStat(@PathVariable Long courseId) {
        return modelMapper.map(courseService.courseStat(courseId), CourseStatDto.class);
    }


}
