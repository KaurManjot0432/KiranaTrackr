package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.user.UserRequestDTO;
import com.example.KiranaTrackr.dtos.user.UserRequestDTOMapper;
import com.example.KiranaTrackr.dtos.user.UserResponseDTO;
import com.example.KiranaTrackr.dtos.user.UserResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.services.user.UserService;
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

    /**
     * Creates a new user based on the provided UserRequestDTO.
     *
     * @param userRequestDTO The DTO containing information about the new user.
     * @return ResponseEntity with the created user information and HTTP status.
     */
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

    /**
     * Retrieves user information by the specified userId.
     *
     * @param userId The unique identifier of the user.
     * @return ResponseEntity with the user information and HTTP status.
     */
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