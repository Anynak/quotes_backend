package com.cameleoon.quotes.controllers;

import com.cameleoon.quotes.dto.mapers.UserMapper;
import com.cameleoon.quotes.dto.requests.UserRequest;
import com.cameleoon.quotes.dto.responses.UserResponse;
import com.cameleoon.quotes.models.User;
import com.cameleoon.quotes.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/v1")
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Create a new user", description = "Returns a new user")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        User newUser = userService.createUser(userRequest);
        UserResponse userResponse = userMapper.toUserResponse(newUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get User by id", description = "Returns a user as per the id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }
}
