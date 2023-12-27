package com.example.KiranaTrackr.Dtos.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}