package ru.croc.coder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.controller.dto.SolutionDto;
import ru.croc.coder.controller.dto.UserDto;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.CourseService;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.UserContext;

@RestController
public class SolutionController {

    private ExerciseService exerciseService;
    private UserRepository userRepository;
    private UserContext userContext;
    private CourseService courseService;
    private ModelMapper modelMapper = new ModelMapper();

    public SolutionController(ExerciseService exerciseService,
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
    @Operation(summary = "Authenticate user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated user", content = {
                    @Content(schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "401", description = "Authentication failed")
    })
    @PostMapping("/exercises/{exerciseId}/solutions")
    public SolutionDto submit(@PathVariable Long exerciseId, @RequestBody String text) {
        Solution solution = exerciseService.submit(exerciseId, text);
        return modelMapper.map(solution, SolutionDto.class);
    }




}
