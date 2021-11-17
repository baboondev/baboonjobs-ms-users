package com.baboondev.baboonjobsmsusers.repositories;

import com.baboondev.baboonjobsmsusers.models.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepository extends MongoRepository<Role, String> {
    @Query("{ 'name' : ?0 }")
    Role findByName(String name);
}
