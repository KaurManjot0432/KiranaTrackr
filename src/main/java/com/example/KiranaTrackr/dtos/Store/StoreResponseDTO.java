package com.example.KiranaTrackr.dtos.Store;

import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.models.enums.CurrencyType;
import lombok.*;


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
    private User storeOwner;
    private CurrencyType localCurrency;
}
