package com.example.KiranaTrackr.dtos.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}
