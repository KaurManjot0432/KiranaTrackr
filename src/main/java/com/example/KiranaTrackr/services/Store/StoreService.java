package com.example.KiranaTrackr.services.Store;

import com.example.KiranaTrackr.dtos.Store.StoreRequestDTO;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;

public interface StoreService {
    StoreResponseDTO createStore(StoreRequestDTO storeRequestDTO) ;
}
