package com.example.KiranaTrackr.services.Transaction;

import com.example.KiranaTrackr.dtos.Store.StoreResponseDTO;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImpl(StoreRepository storeRepository,
                                  UserRepository userRepository,
                                  TransactionRepository transactionRepository
    ) {
        this.storeRepository = storeRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }
    @Override
    public TransactionResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) {
        User user = userRepository.findById(transactionRequestDTO.getCustomerId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + transactionRequestDTO.getCustomerId()));

        Store store = storeRepository.findById(transactionRequestDTO.getStoreId())
                .orElseThrow(() -> new StoreNotFoundException("Store not found with ID: " + transactionRequestDTO.getStoreId()));

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionRequestDTO, transaction);

        transaction.setCustomer(user);
        transaction.setStore(store);

        Transaction savedTransaction = transactionRepository.save(transaction);

        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        BeanUtils.copyProperties(savedTransaction, responseDTO);

        return responseDTO;
    }
}
