package com.example.KiranaTrackr.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.KiranaTrackr.models.enums.PaymentType;
import com.example.KiranaTrackr.models.enums.CurrencyType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentType paymentType;

    @NotNull
    private CurrencyType currencyType;

    @DBRef
    @NotNull
    private User customer;

    @DBRef
    @NotNull
    private Store store;

    @NotNull
    private String storeId;

    @NotNull
    private String customerId;

    @CreatedDate
    private LocalDateTime createdAt;
}
