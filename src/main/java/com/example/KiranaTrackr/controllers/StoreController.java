package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.store.StoreRequestDTO;
import com.example.KiranaTrackr.dtos.store.StoreRequestDTOMapper;
import com.example.KiranaTrackr.dtos.store.StoreResponseDTO;
import com.example.KiranaTrackr.dtos.store.StoreResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.services.store.StoreService;
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

    /**
     * Creates a new store based on the provided StoreRequestDTO.
     *
     * @param storeRequestDTO The DTO containing information about the new store.
     * @return ResponseEntity with the created store information and HTTP status.
     */
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

    /**
     * Retrieves store information by the specified storeId.
     *
     * @param storeId The unique identifier of the store.
     * @return ResponseEntity with the store information and HTTP status.
     */
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
