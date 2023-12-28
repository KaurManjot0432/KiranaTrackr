package com.example.KiranaTrackr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Email
    @Indexed(unique = true)
    private String email;

    @NotNull
    @Size(min=5)
    private String password;

    @NotNull
    @Size(min=10, max=10)
    @Pattern(regexp = "[0-9]{10}")
    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;

}
