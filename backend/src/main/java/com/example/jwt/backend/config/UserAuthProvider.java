package com.example.jwt.backend.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.backend.dto.UserDto;
import com.example.jwt.backend.entity.User;
import com.example.jwt.backend.exception.AppException;
import com.example.jwt.backend.mapper.UserMapper;
import com.example.jwt.backend.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    
    @PostConstruct //run after bean is created
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto userDto) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000); // 3_600_000 = 60 × 60 × 1000 ms (1hour)
        return JWT.create()
                .withIssuer(userDto.getLogin())                         // 'iss' claim
                .withIssuedAt(now)                                      // 'iat'
                .withExpiresAt(validity)                                // 'exp'
                .withClaim("firstName", userDto.getFirstName())
                .withClaim("lastName", userDto.getLastName())
                .sign(Algorithm.HMAC256(secretKey));                    // sign using secretKey
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDto userDto = UserDto.builder()
                                .login(decoded.getIssuer())
                                .firstName(decoded.getClaim("firstName").asString())
                                .lastName(decoded.getClaim("lastName").asString())
                                .build();
        
        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        User user = userRepository.findByLogin(decoded.getIssuer())
                    .orElseThrow(()-> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());
    }

}
