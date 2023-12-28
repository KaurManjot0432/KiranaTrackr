package com.example.KiranaTrackr.services.report;

import com.example.KiranaTrackr.dtos.report.ReportResult;
import com.example.KiranaTrackr.dtos.transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.dtos.transaction.TransactionResponseDTOMapper;
import com.example.KiranaTrackr.models.Transaction;
import com.example.KiranaTrackr.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportGenerator implements ReportService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportGenerator(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ReportResult generateDailyReport(String date) {
        LocalDate startDate = LocalDate.parse(date);
        LocalDate endDate = startDate.plusDays(1);
        List<Transaction> transactions = transactionRepository.findByCreatedAt(startDate, endDate);
        List<TransactionResponseDTO> responseDTOs = transactions
                .stream()
                .map(TransactionResponseDTOMapper::mapToTransactionResponseDTO)
                .collect(Collectors.toList());
        return ReportResult.builder()
                .startDate(startDate)
                .endDate(endDate)
                .transactions(responseDTOs)
                .balance(calculateBalance(transactions))
                .build();
    }

    @Override
    public ReportResult generateCustomReport(String startDate, String endDate) {
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);
        List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(localStartDate, localEndDate);
        List<TransactionResponseDTO> responseDTOs = transactions
                .stream()
                .map(TransactionResponseDTOMapper::mapToTransactionResponseDTO)
                .collect(Collectors.toList());
        return ReportResult.builder()
                .startDate(localStartDate)
                .endDate(localEndDate)
                .transactions(responseDTOs)
                .balance(calculateBalance(transactions))
                .build();
    }
    private BigDecimal calculateBalance(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
