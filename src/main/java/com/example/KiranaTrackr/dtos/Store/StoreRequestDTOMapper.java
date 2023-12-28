package com.example.KiranaTrackr.dtos.Store;

import com.example.KiranaTrackr.dtos.Store.StoreRequestDTO;
import com.example.KiranaTrackr.models.Store;

public class StoreRequestDTOMapper {
    public static Store mapToStoreRequestDTO (StoreRequestDTO storeRequestDTO) {
        return Store.builder()
                .name(storeRequestDTO.getName())
                .description(storeRequestDTO.getDescription())
                .address(storeRequestDTO.getAddress())
                .storeOwnerId(storeRequestDTO.getStoreOwnerId())
                .localCurrency(storeRequestDTO.getLocalCurrency())
                .build();
    }
}
