package ru.croc.coder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.ExerciseRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.ExerciseService;
import ru.croc.coder.service.NotFoundException;
import ru.croc.coder.service.UserContext;

@RestController
public class SolutionController {

    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserContext userContext;

    @PostMapping("/users/{userId}/exercises/{exerciseId}/solutions")
    public Solution submit(@PathVariable Long userId, @PathVariable Long exerciseId, @RequestBody String text) {
        return exerciseService.submit(userId, exerciseId, text);
    }

    @PostMapping("/auth/{userId}")
    public User auth(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        userContext.setCurrentUser(user);
        return user;
    }
}
