package com.example.KiranaTrackr.Dtos.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
