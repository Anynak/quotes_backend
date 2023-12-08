package com.cameleoon.quotes.dto.responses;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteResponse {

    private Long voteId;


    private Integer voteValue;


    private Long userId;


    private Long quoteId;


    private LocalDateTime creationTime;
}
