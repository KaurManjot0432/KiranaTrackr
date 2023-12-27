package com.example.KiranaTrackr.repositories;

import com.example.KiranaTrackr.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {

}
