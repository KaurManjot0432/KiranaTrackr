package com.example.KiranaTrackr.dtos.Transaction;

import com.example.KiranaTrackr.models.Transaction;

public class TransactionRequestDTOMapper {
    public static Transaction mapToTransactionRequestDTO (TransactionRequestDTO transactionRequestDTO) {
        return Transaction.builder()
                .amount(transactionRequestDTO.getAmount())
                .paymentType(transactionRequestDTO.getPaymentType())
                .currencyType(transactionRequestDTO.getCurrencyType())
                .storeId(transactionRequestDTO.getStoreId())
                .customerId(transactionRequestDTO.getCustomerId())
                .build();
    }
}
