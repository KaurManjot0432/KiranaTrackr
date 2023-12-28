package com.example.KiranaTrackr.dtos.Store;

import com.example.KiranaTrackr.models.Store;

public class StoreResponseDTOMapper {
    public static StoreResponseDTO mapToStoreResponseDTO (Store store) {
        return StoreResponseDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .description(store.getDescription())
                .address(store.getAddress())
                .storeOwnerId(store.getStoreOwnerId())
                .localCurrency(store.getLocalCurrency())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
