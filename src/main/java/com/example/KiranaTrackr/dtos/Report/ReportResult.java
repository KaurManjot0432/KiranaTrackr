package com.example.KiranaTrackr.dtos.Report;

import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.models.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportResult {
    private List<TransactionResponseDTO> transactions;
    private BigDecimal balance;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer year;
    private Integer month;
}
