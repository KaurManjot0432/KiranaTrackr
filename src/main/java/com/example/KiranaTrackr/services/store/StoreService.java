package com.example.KiranaTrackr.services.store;

import com.example.KiranaTrackr.models.Store;

public interface StoreService {
    public Store createStore(Store store) ;

    public Store getStoreById(String storeId);
}
