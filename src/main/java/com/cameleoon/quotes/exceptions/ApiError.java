package com.cameleoon.quotes.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiError {
    private String error;
    private String userMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiError(String error, String userMessage) {
        super();
        this.userMessage = userMessage;
        this.error = error;
        timestamp = LocalDateTime.now();
    }
}
