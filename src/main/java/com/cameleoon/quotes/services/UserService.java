package com.cameleoon.quotes.services;

import com.cameleoon.quotes.dto.mapers.UserMapper;
import com.cameleoon.quotes.dto.requests.UserRequest;
import com.cameleoon.quotes.exceptions.UserNotFoundException;
import com.cameleoon.quotes.models.User;
import com.cameleoon.quotes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest) {

        User newUser = userMapper.toUser(userRequest);
        newUser.setRegistrationTime(LocalDateTime.now());
        newUser.setPasswordHash(userRequest.getPassword());
        return userRepository.save(newUser);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user with id " + userId + " is not found"));

    }
}
