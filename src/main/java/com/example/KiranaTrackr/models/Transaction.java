package com.example.KiranaTrackr.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.KiranaTrackr.models.enums.PaymentType;
import com.example.KiranaTrackr.models.enums.CurrencyType;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CurrencyType currencyType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne
    @NotNull
    private Store store;
}
