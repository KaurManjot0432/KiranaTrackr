package com.example.KiranaTrackr.dtos.User;

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
