package com.example.KiranaTrackr.dtos.User;

import com.example.KiranaTrackr.models.User;

public class UserResponseDTOMapper {
    public static UserResponseDTO mapToUserResponseDTO (User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
