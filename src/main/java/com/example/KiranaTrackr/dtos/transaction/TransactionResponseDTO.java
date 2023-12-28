package com.example.KiranaTrackr.dtos.transaction;

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
