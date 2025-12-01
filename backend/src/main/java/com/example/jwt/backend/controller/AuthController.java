package com.example.jwt.backend.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.backend.config.UserAuthProvider;
import com.example.jwt.backend.dto.CredentialsDto;
import com.example.jwt.backend.dto.SignUpDto;
import com.example.jwt.backend.dto.UserDto;
import com.example.jwt.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
        // ResponseEntity.created(...)
        // Spring return = Status: 201 Created

        // URI.create("/users/10")
        // tells the client user can be found at /users/10
        
        //.body(user)
        // Puts the JSON object in the response body

        // Final output sample = 
        // HTTP/1.1 201 Created
        // Location: /users/10
        // Content-Type: application/json

        // {
        //    "id": 10,
        //    "firstName": "John",
        //    "lastName": "Doe",
        //    "login": "john@example.com",
        //    "token": null
        // }
    }

}
