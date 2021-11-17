package com.baboondev.baboonjobsmsusers.controllers;

import com.baboondev.baboonjobsmsusers.dto.UserDto;
import com.baboondev.baboonjobsmsusers.exceptions.InvalidCredentialsException;
import com.baboondev.baboonjobsmsusers.exceptions.UserExistsException;
import com.baboondev.baboonjobsmsusers.exceptions.UserNotFoundException;
import com.baboondev.baboonjobsmsusers.models.User;
import com.baboondev.baboonjobsmsusers.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody User user) {
        try {
            UserDto userDto = userService.signIn(user);
            logger.info("User logged in successfully");
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            logger.error(String.format("User %s not found", user.getEmail()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidCredentialsException e) {
            logger.error(String.format("Invalid credentials for user %s", user.getEmail()));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestParam String role, @RequestBody User user) {
        try {
            userService.signUp(user, role);
            logger.info("User signed up successfully");
            return ResponseEntity.ok().build();
        } catch (UserExistsException e) {
            logger.error(String.format("User %s already exists", user.getEmail()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
