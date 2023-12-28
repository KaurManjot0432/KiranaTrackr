package com.example.KiranaTrackr.repositories;

import com.example.KiranaTrackr.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByStoreId(String storeId);
}
