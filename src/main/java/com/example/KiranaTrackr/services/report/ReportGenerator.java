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

    /**
     * Generates a daily report for the specified date.
     *
     * @param date The date for which the report is generated.
     * @return ReportResult containing daily transaction details and balance.
     */
    @Override
    public ReportResult generateDailyReport(String storeId, String date) {
        LocalDate startDate = LocalDate.parse(date);
        LocalDate endDate = startDate.plusDays(1);
        List<Transaction> transactions = transactionRepository.findByCreatedAtAndStoreId(storeId, startDate, endDate);
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

    /**
     * Generates a custom report for a specified date range.
     *
     * @param startDate The start date of the report period.
     * @param endDate   The end date of the report period.
     * @return ReportResult containing transaction details and balance for the specified period.
     */
    @Override
    public ReportResult generateCustomReport(String storeId, String startDate, String endDate) {
        LocalDate localStartDate = LocalDate.parse(startDate);
        LocalDate localEndDate = LocalDate.parse(endDate);
        List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(storeId, localStartDate, localEndDate);
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
