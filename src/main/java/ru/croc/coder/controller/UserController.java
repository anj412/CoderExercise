package ru.croc.coder.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.controller.dto.*;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.UserCourseRegistration;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.CourseService;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.UserContext;
import ru.croc.coder.service.UserService;

import java.util.List;

@RestController
public class UserController {
    private ExerciseService exerciseService;
    private UserRepository userRepository;
    private UserContext userContext;
    private CourseService courseService;
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    public UserController(ru.croc.coder.service.ExerciseService exerciseService,
                          UserRepository userRepository,
                          UserContext userContext,
                          CourseService courseService,
                          UserService userService) {
        this.exerciseService = exerciseService;
        this.userRepository = userRepository;
        this.userContext = userContext;
        this.courseService = courseService;
        this.userService = userService;

        //this.modelMapper = modelMapper;
    }
    //АУТЕНФИКАЦИЯ
    @GetMapping("/auth/{userId}/{password}")
    public UserDto auth(@PathVariable Long userId, @PathVariable String password) {
        return modelMapper.map(userService.authorization(userId, password), UserDto.class);
    }

    @GetMapping("/auth_by_email/{email}/{password}")
    public UserDto auth(@PathVariable String email, @PathVariable String password) {
        return modelMapper.map(userService.authorization(email, password), UserDto.class);
    }

    //РЕГИСТРАЦИЯ
    @PostMapping("/reg/{fName}/{lName}/{email}/{pass}")
    public UserDto reg(@PathVariable String fName, @PathVariable String lName,
                       @PathVariable String email, @PathVariable String pass) {
        return modelMapper.map(userService.addUser(fName, lName, email, pass), UserDto.class);
    }
    //make user a teacher
    @GetMapping("/teach/{userId}")
    public UserDto teach(@PathVariable Long userId) {
        return modelMapper.map(userService.commandSetUserToTeacher(userId), UserDto.class);
    }

    //Reg user to course
    @PostMapping("/enroll/{courseId}/{userId}")
    public UserCourseRegistrationDto enroll(@PathVariable Long courseId, @PathVariable Long userId) {
        return modelMapper.map(courseService.commandToAddUserCourseRegistration(courseId, userId),
                UserCourseRegistrationDto.class);
    }

    @GetMapping("/users/stat/{userId}")
    public UserStatDto usStat(@PathVariable Long userId){
        return modelMapper.map(userService.buildUserStat(userId), UserStatDto.class);
    }

    @PostMapping("/diktant")
    public List<SolutionDto> diktant() {
        return modelMapper.map(userService.totalDiktant(), new TypeToken<List<SolutionDto>>() {}.getType());
    }

}
