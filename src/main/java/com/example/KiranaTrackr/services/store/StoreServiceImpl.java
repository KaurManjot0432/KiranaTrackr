package com.example.KiranaTrackr.services.Store;

import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.repositories.StoreRepository;
import com.example.KiranaTrackr.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Store createStore(Store store) {
        User storeOwner = userRepository.findById(store.getStoreOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        store.setStoreOwner(storeOwner);

        return storeRepository.save(store);
    }

    @Override
    public Store getStoreById(String storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id : " + storeId));
    }
}
