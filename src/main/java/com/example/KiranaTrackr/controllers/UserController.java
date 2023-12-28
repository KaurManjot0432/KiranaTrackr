package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTOMapper;
import com.example.KiranaTrackr.dtos.User.UserRequestDTO;
import com.example.KiranaTrackr.dtos.User.UserRequestDTOMapper;
import com.example.KiranaTrackr.dtos.User.UserResponseDTO;
import com.example.KiranaTrackr.dtos.User.UserResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            User user = UserRequestDTOMapper.mapToUserRequestDTO(userRequestDTO);

            User response = userService.createUser(user);

            UserResponseDTO responseDTO = UserResponseDTOMapper.mapToUserResponseDTO(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        try {
            UserResponseDTO responseDTO = UserResponseDTOMapper.mapToUserResponseDTO(
                    userService.getUserById(userId)
            );

            return ResponseEntity.ok(responseDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request");
    }
}