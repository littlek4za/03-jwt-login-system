package com.example.jwt.backend.dto;

public record SignUpDto(String firstName, String lastName, String login, char[] password) {

}

// package com.example.jwt.backend.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class SignUpDto {
//     private String firstName;
//     private String lastName;
//     private String login;
//     private String password;
// }