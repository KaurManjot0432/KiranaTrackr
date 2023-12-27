package com.example.KiranaTrackr.services.User;

import com.example.KiranaTrackr.dtos.User.UserRequestDTO;
import com.example.KiranaTrackr.dtos.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}