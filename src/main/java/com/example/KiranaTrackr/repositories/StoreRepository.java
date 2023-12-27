package com.example.KiranaTrackr.repositories;

import com.example.KiranaTrackr.models.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store,String> {
}
