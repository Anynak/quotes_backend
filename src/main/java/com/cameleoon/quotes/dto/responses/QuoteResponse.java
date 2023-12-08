package com.cameleoon.quotes.dto.responses;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuoteResponse {
    private Long quoteId;

    private String content;

    private Author author;

    private LocalDateTime creationTime;
}
