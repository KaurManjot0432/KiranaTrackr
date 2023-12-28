package com.example.KiranaTrackr.services.Transaction;

import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(String transactionId);

    List<Transaction> getTransactionsByStoreId(String storeId);
}
