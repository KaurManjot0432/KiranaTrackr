package com.example.KiranaTrackr.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import com.example.KiranaTrackr.models.enums.CurrencyType;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "stores")
public class Store {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    @DBRef
    private User storeOwner;

    @NotNull
    private String storeOwnerId;

    @Enumerated(EnumType.STRING)
    private CurrencyType localCurrency;

    @CreatedDate
    private LocalDateTime createdAt;

}