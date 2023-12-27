package com.example.KiranaTrackr.services;

import com.example.KiranaTrackr.dtos.User.UserRequestDTO;
import com.example.KiranaTrackr.dtos.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}