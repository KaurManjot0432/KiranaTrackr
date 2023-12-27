package com.example.KiranaTrackr.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import com.example.KiranaTrackr.models.enums.CurrencyType;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Entity
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

    @NotNull (message = "Store Name is required")
    private String name;

    @NotNull (message = "Store Description is required")
    private String description;

    @NotNull (message = "Store Address is required")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @DBRef
    private User storeOwner;

    @Enumerated(EnumType.STRING)
    private CurrencyType localCurrency;

}