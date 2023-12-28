package com.example.KiranaTrackr.dtos.user;

import com.example.KiranaTrackr.models.User;

public class UserRequestDTOMapper {
    public static User mapToUserRequestDTO (UserRequestDTO userRequestDTO) {
        return User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .build();
    }
}
