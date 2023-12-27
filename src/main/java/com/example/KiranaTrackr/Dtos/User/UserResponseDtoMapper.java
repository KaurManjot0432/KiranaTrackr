package com.example.KiranaTrackr.Dtos.User;

import com.example.KiranaTrackr.models.User;

public class UserResponseDtoMapper {

    public static UserResponseDto mapUserResponseToUser(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
