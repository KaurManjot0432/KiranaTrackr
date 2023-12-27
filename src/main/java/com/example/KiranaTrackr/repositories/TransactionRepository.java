package com.example.KiranaTrackr.repositories;

import com.example.KiranaTrackr.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
