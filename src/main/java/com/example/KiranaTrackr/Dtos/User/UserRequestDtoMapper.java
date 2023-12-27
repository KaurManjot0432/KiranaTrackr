package com.example.KiranaTrackr.Dtos.User;

import com.example.KiranaTrackr.models.User;

public class UserRequestDtoMapper {

    public static User mapUserRequestToUser(UserRequestDto userRequestDto){
        return User.builder().email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .password(userRequestDto.getPassword())
                .build();
    }
}
