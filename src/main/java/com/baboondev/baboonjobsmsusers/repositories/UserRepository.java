package com.baboondev.baboonjobsmsusers.repositories;

import com.baboondev.baboonjobsmsusers.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);
}
