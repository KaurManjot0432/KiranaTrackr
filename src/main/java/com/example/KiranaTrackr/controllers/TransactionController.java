package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTOMapper;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.models.Transaction;
import com.example.KiranaTrackr.services.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;
import com.example.KiranaTrackr.exceptions.TransactionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        try {
            Transaction transaction = TransactionRequestDTOMapper.mapToTransactionRequestDTO(transactionRequestDTO);

            Transaction response = transactionService.createTransaction(transaction);

            TransactionResponseDTO responseDTO = TransactionResponseDTOMapper.mapToTransactionResponseDTO(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (StoreNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        } catch (Exception e) {
            logger.error("Error occurred while processing createTransaction request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Some Error occurred while processing your request");
        }
    }


    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable String transactionId) {
        try {
            TransactionResponseDTO responseDTO = TransactionResponseDTOMapper.mapToTransactionResponseDTO(
                    transactionService.getTransactionById(transactionId)
            );

            return ResponseEntity.ok(responseDTO);
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        } catch (Exception e) {
            logger.error("Error occurred while processing getTransactionById request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Some Error occurred while processing your request");
        }
    }


    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getTransactionsByStoreId(@PathVariable String storeId) {
        try {
            List<TransactionResponseDTO> responseDTOs = transactionService
                    .getTransactionsByStoreId(storeId)
                    .stream()
                    .map(TransactionResponseDTOMapper::mapToTransactionResponseDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            logger.error("Error occurred while processing getTransactionsByStoreId request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Some Error occurred while processing your request");
        }
    }

}
