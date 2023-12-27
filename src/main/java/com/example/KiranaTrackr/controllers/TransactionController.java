package com.example.KiranaTrackr.controllers;

import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.services.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        try {
            TransactionResponseDTO responseDTO = transactionService.createTransaction(transactionRequestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            // Handle the case where the user is not found
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Add other endpoints for fetching transactions, generating reports, etc.
}
