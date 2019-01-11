package com.ija.IJAcrypto.controller;

import com.ija.IJAcrypto.exception.UserAlreadyExistsException;
import com.ija.IJAcrypto.model.User;
import com.ija.IJAcrypto.repository.UserRepository;
import com.ija.IJAcrypto.response.UserResponse;
import com.ija.IJAcrypto.service.CreateUserService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private CreateUserService createUserService;

    private UserRepository userRepository;

    public UserController(CreateUserService createUserService, UserRepository userRepository) {
        this.createUserService = createUserService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseBody
    public UserResponse create(
            @RequestBody User user
    ) throws NoSuchAlgorithmException, UserAlreadyExistsException {
        UserResponse userResponse = new UserResponse(createUserService.create(user));
        return userResponse;
    }

    @GetMapping
    @ResponseBody
    public List<UserResponse> getAll() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
