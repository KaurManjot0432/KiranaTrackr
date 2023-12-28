package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.Store.StoreRequestDTO;
import com.example.KiranaTrackr.dtos.Store.StoreRequestDTOMapper;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;
import com.example.KiranaTrackr.dtos.Store.StoreResponseDTOMapper;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.exceptions.TransactionNotFoundException;
import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.services.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<?> createStore(@RequestBody StoreRequestDTO storeRequestDTO) {
        try {
            Store store = StoreRequestDTOMapper.mapToStoreRequestDTO(storeRequestDTO);
            Store response = storeService.createStore(store);
            StoreResponseDTO responseDTO = StoreResponseDTOMapper.mapToStoreResponseDTO(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<?> getStoreById(@PathVariable String storeId) {
        try {
            StoreResponseDTO responseDTO = StoreResponseDTOMapper.mapToStoreResponseDTO(
                    storeService.getStoreById(storeId)
            );

            return ResponseEntity.ok(responseDTO);
        } catch (StoreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request");
    }
}
