package com.example.KiranaTrackr.dtos.Transaction;

import com.example.KiranaTrackr.models.Transaction;

public class TransactionResponseDTOMapper {
    public static TransactionResponseDTO mapToTransactionResponseDTO (Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .paymentType(transaction.getPaymentType())
                .currencyType(transaction.getCurrencyType())
                .storeId(transaction.getStoreId())
                .customerId(transaction.getCustomerId())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
