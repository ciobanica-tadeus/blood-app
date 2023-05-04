package com.example.bloodapp.controllers;

import com.example.bloodapp.controllers.dtos.BaseResponse;
import com.example.bloodapp.controllers.dtos.UserResponse;
import com.example.bloodapp.domain.entity.User;
import com.example.bloodapp.domain.repositories.UserRepository;
import com.example.bloodapp.exceptions.DuplicateEntityException;
import com.example.bloodapp.exceptions.NotFoundException;
import com.example.bloodapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<BaseResponse> getUser(@RequestBody UserResponse userResponse){
        try {
            return new ResponseEntity<>(
                    userService.loginUser(userResponse)
                    ,
                    HttpStatus.OK
            );
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
