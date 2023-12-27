package com.example.KiranaTrackr.services.Store;

import com.example.KiranaTrackr.dtos.Store.StoreRequestDTO;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;
import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.repositories.StoreRepository;
import com.example.KiranaTrackr.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
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
    public StoreResponseDTO createStore(StoreRequestDTO storeRequestDTO) {
        User storeOwner = userRepository.findById(storeRequestDTO.getStoreOwnerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Store store = new Store();
        BeanUtils.copyProperties(storeRequestDTO, store);
        store.setStoreOwner(storeOwner);

        Store savedStore = storeRepository.save(store);

        StoreResponseDTO responseDTO = new StoreResponseDTO();
        BeanUtils.copyProperties(savedStore, responseDTO);

        return responseDTO;
    }
}
