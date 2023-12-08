package com.cameleoon.quotes.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class QuoteRequest {

    private Long quoteId = null;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    @Positive
    private Long authorId;

}
