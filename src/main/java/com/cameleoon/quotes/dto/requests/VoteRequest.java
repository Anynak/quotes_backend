package com.cameleoon.quotes.dto.requests;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VoteRequest {


    @NotNull
    @Min(-1)
    @Max(1)
    private Integer voteValue;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long quoteId;

}
