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

            return new ResponseEntity<>(TransactionResponseDTOMapper.mapToTransactionResponseDTO(response), HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (StoreNotFoundException e) {
            return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while processing createTransaction request", e);
            return new ResponseEntity<>("Some Error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransactionById(@PathVariable String transactionId) {
        try {
            TransactionResponseDTO responseDTO = transactionService.getTransactionById(transactionId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            // Handle the case where the transaction is not found
            return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while processing getTransactionById request", e);
            return new ResponseEntity<>("Some Error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getTransactionsByStoreId(@PathVariable String storeId) {
        try {
            List<TransactionResponseDTO> responseDTOs = transactionService.getTransactionsByStoreId(storeId);

            if (responseDTOs.isEmpty()) {
                return new ResponseEntity<>("No transactions found for the specified store", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while processing getTransactionsByStoreId request", e);
            return new ResponseEntity<>("Some Error occurred while processing your request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
