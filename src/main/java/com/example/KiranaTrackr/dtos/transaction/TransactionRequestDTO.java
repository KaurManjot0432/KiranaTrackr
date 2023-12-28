package com.example.KiranaTrackr.dtos.transaction;

import com.example.KiranaTrackr.models.enums.CurrencyType;
import com.example.KiranaTrackr.models.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionRequestDTO {
    private BigDecimal amount;
    private PaymentType paymentType;
    private CurrencyType currencyType;
    private String customerId;
    private String storeId;
}
