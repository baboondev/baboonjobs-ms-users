package com.baboondev.baboonjobsmsusers.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
