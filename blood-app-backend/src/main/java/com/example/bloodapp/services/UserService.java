package com.example.bloodapp.services;

import com.example.bloodapp.controllers.dtos.UserResponse;
import com.example.bloodapp.domain.entity.User;
import com.example.bloodapp.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse loginUser(UserResponse userResponse) {
        Optional<User> users = userRepository.findByEmailAndPassword(
                userResponse.getEmail(),
                userResponse.getPassword()
        );
        if (users.isEmpty()) {
            throw new NoSuchElementException("Can't find user with inserted email and password!");
        }
        return new UserResponse(users.get());
    }

}
