package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.Store.StoreRequestDTO;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;
import com.example.KiranaTrackr.services.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        StoreResponseDTO responseDTO = storeService.createStore(storeRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
