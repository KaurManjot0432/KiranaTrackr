package com.example.KiranaTrackr.dtos.Transaction;

import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.models.enums.CurrencyType;
import com.example.KiranaTrackr.models.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionResponseDTO {
    private String id;
    private BigDecimal amount;
    private PaymentType paymentType;
    private CurrencyType currencyType;
    private String customerId;
    private String storeId;
    private LocalDateTime createdAt;
}
