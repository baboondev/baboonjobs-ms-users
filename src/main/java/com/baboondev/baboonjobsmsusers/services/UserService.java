package com.baboondev.baboonjobsmsusers.services;

import com.baboondev.baboonjobsmsusers.dto.UserDto;
import com.baboondev.baboonjobsmsusers.exceptions.InvalidCredentialsException;
import com.baboondev.baboonjobsmsusers.exceptions.InvalidRoleException;
import com.baboondev.baboonjobsmsusers.exceptions.UserExistsException;
import com.baboondev.baboonjobsmsusers.exceptions.UserNotFoundException;
import com.baboondev.baboonjobsmsusers.mappers.UserMapper;
import com.baboondev.baboonjobsmsusers.models.Role;
import com.baboondev.baboonjobsmsusers.models.User;
import com.baboondev.baboonjobsmsusers.repositories.RoleRepository;
import com.baboondev.baboonjobsmsusers.repositories.UserRepository;
import com.baboondev.baboonjobsmsusers.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDto signIn(User user) throws InvalidCredentialsException, UserNotFoundException {
        User userDB = userRepository.findByEmail(user.getEmail());

        if (userDB == null)
            throw new UserNotFoundException("User does not exists");

        String password = user.getPassword();
        String passwordDB = userDB.getPassword();

        if (!passwordEncoder.matches(password, passwordDB))
            throw new InvalidCredentialsException("Invalid Credentials");

        UserDto userDto = UserMapper.mapUserToDto(userDB);

        String token = JwtUtil.generateToken(userDB);

        userDto.setToken(token);

        return userDto;
    }

    public void signUp(User user, String role) throws InvalidRoleException, UserExistsException {
        Role roleDB = roleRepository.findByName(role);

        if (roleDB == null)
            throw new InvalidRoleException("Role does not exists");

        User userDB = userRepository.findByEmail(user.getEmail());

        if (userDB != null)
            throw new UserExistsException("User already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleDB);

        userRepository.save(user);
    }
}
