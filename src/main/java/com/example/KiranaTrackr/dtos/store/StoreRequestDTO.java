package com.example.KiranaTrackr.dtos.store;

import com.example.KiranaTrackr.models.enums.CurrencyType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StoreRequestDTO {
    private String name;
    private String description;
    private String address;
    private String storeOwnerId;
    private CurrencyType localCurrency;
}
