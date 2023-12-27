package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.User.UserRequestDTO;
import com.example.KiranaTrackr.dtos.User.UserResponseDTO;
import com.example.KiranaTrackr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}