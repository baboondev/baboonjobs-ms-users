package com.baboondev.baboonjobsmsusers.mappers;

import com.baboondev.baboonjobsmsusers.dto.UserDto;
import com.baboondev.baboonjobsmsusers.models.User;

public class UserMapper {
    public static UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.set_id(user.get_id());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
