package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.transaction.TransactionRequestDTOMapper;
import com.example.KiranaTrackr.dtos.transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.dtos.transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.transaction.TransactionResponseDTOMapper;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.models.Transaction;
import com.example.KiranaTrackr.services.transaction.TransactionService;
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
            return handleException(e);
        }
    }

    /**
     * Retrieves transaction information by the specified transactionId.
     *
     * @param transactionId The unique identifier of the transaction.
     * @return ResponseEntity with the transaction information and HTTP status.
     */
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
            return handleException(e);
        }
    }

    /**
     * Retrieves a list of transactions for a given storeId.
     *
     * @param storeId The unique identifier of the store.
     * @return ResponseEntity with a list of transaction information and HTTP status.
     */
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
            return handleException(e);
        }
    }

    /**
     * Handles exceptions that may occur during transaction-related operations.
     *
     * @param e The exception that occurred.
     * @return ResponseEntity with an error message and HTTP status.
     */
    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request");
    }

}
