package com.example.jwt.backend.mapper;

import org.springframework.stereotype.Component;

import com.example.jwt.backend.dto.SignUpDto;
import com.example.jwt.backend.dto.UserDto;
import com.example.jwt.backend.entity.User;

@Component
public class UserMapper {

   public UserDto toUserDto(User user) {
    if(user == null) {
        return null;
    }

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setLogin(user.getLogin());
    userDto.setToken(null);

    return userDto;
   }

   public User signUpToUser(SignUpDto signUpDto) {
    if(signUpDto == null) {
        return null;
    }

    User user = new User();
    user.setFirstName(signUpDto.firstName());
    user.setLastName(signUpDto.lastName());
    user.setLogin(signUpDto.login());

    return user;
   }

}
