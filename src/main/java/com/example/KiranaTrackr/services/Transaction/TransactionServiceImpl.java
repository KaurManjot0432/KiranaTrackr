package com.example.KiranaTrackr.services.Transaction;

import com.example.KiranaTrackr.dtos.Transaction.TransactionRequestDTO;
import com.example.KiranaTrackr.dtos.Transaction.TransactionResponseDTO;
import com.example.KiranaTrackr.exceptions.StoreNotFoundException;
import com.example.KiranaTrackr.exceptions.UserNotFoundException;
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
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        User user = userRepository.findById(transactionRequestDTO.getCustomerId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + transactionRequestDTO.getCustomerId()));

        Store store = storeRepository.findById(transactionRequestDTO.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + transactionRequestDTO.getStoreId()));

        // Convert the transaction amount to the store's local currency
        BigDecimal convertedAmount = currencyConversionService.convert(
                transactionRequestDTO.getAmount(),
                transactionRequestDTO.getCurrencyType(),
                store.getLocalCurrency()
        );

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionRequestDTO, transaction);

        transaction.setAmount(convertedAmount);
        transaction.setCustomer(user);
        transaction.setStore(store);

        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        BeanUtils.copyProperties(savedTransaction, responseDTO);

        return responseDTO;
    }
}
