package com.example.KiranaTrackr.dtos.store;

import com.example.KiranaTrackr.models.enums.CurrencyType;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StoreResponseDTO {
    private String id;
    private String name;
    private String description;
    private String address;
    private String storeOwnerId;
    private CurrencyType localCurrency;
    private LocalDateTime createdAt;
}
