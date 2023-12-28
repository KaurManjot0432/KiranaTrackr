package com.example.KiranaTrackr.services.Transaction;

import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;
import com.example.KiranaTrackr.exceptions.TransactionNotFoundException;
import com.example.KiranaTrackr.models.Store;
import com.example.KiranaTrackr.models.Transaction;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.repositories.StoreRepository;
import com.example.KiranaTrackr.repositories.TransactionRepository;
import com.example.KiranaTrackr.repositories.UserRepository;
import com.example.KiranaTrackr.services.currencyConversion.CurrencyConversionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public TransactionServiceImpl(StoreRepository storeRepository,
                                  UserRepository userRepository,
                                  TransactionRepository transactionRepository,
                                  CurrencyConversionService currencyConversionService) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        User user = userRepository.findById(transaction.getCustomerId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + transaction.getCustomerId()));

        Store store = storeRepository.findById(transaction.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + transaction.getStoreId()));

        // Convert the transaction amount to the store's local currency
        BigDecimal convertedAmount = currencyConversionService.convert(
                transaction.getAmount(),
                transaction.getCurrencyType(),
                store.getLocalCurrency()
        );

        transaction.setAmount(convertedAmount);
        transaction.setCustomer(user);
        transaction.setStore(store);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return savedTransaction;
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + transactionId));

        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByStoreId(String storeId) {
        List<Transaction> transactions = transactionRepository.findByStoreId(storeId);

        return transactions;
    }
}
