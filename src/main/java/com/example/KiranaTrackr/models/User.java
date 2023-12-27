package com.example.KiranaTrackr.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
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

    @NotNull (message = "User Name is required")
    private String name;

    @NotNull (message = "User Email is required")
    @Email
    private String email;

    @NotNull
    @Size(min=5)
    private String password;

    @NotNull
    @Size(min=10, max=10)
    @Pattern(regexp = "[0-9]{10}")
    private String phoneNumber;

}
