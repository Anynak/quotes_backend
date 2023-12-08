package com.cameleoon.quotes.dto.responses;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long userId;
    private String name;
    private String email;
    private LocalDateTime registrationTime;
}
