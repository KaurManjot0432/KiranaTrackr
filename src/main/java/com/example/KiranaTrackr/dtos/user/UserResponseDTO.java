package com.example.KiranaTrackr.dtos.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
}
