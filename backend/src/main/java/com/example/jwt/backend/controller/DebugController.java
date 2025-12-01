package com.example.jwt.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.backend.config.UserAuthProvider;
import com.example.jwt.backend.exception.AppException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DebugController {

    @GetMapping("/debug")
    public Map<String,Object> getTokenInfo(@RequestHeader("Authorization") String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new AppException("No token found", HttpStatus.UNAUTHORIZED);
        }

        String[] authElements = authHeader.split(" ");
        String token = authElements[1];
        DecodedJWT decoded = JWT.decode(token);

        Map<String,Object> decodedClaimsMap = new HashMap<>();
        decoded.getClaims().forEach((key,claim) -> {
            if(claim.isNull()){
                decodedClaimsMap.put(key,null);
            } else {
                decodedClaimsMap.put(key,claim.as(Object.class));
            }
        });

        return decodedClaimsMap;
    }

}
