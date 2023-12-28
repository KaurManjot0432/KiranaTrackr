package com.example.KiranaTrackr.repositories;

import com.example.KiranaTrackr.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByStoreId(String storeId);
    @Query("{ 'createdAt' : { $gte: ?0, $lt: ?1 }, 'storeId' : ?2 }")
    List<Transaction> findByCreatedAtAndStoreId(String storeId, LocalDate startDate, LocalDate endDate);

    @Query("{ 'createdAt' : { $gte: ?0, $lt: ?1 } }")
    List<Transaction> findByCreatedAtBetween(String storeId, LocalDate startDate, LocalDate endDate);
}
