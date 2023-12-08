package com.cameleoon.quotes.controllers;

import com.cameleoon.quotes.dto.mapers.VoteMapper;
import com.cameleoon.quotes.dto.requests.VoteRequest;
import com.cameleoon.quotes.dto.responses.VoteResponse;
import com.cameleoon.quotes.models.Vote;
import com.cameleoon.quotes.services.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/v1")
@Validated
public class VoteController {

    private final VoteMapper voteMapper;
    private final VoteService voteService;

    @Operation(summary = "Create a new vote", description = "Returns a new vote")
    @PostMapping
    public ResponseEntity<VoteResponse> createVote(@RequestBody @Valid VoteRequest voteRequest) {
        Vote newVite = voteService.saveVote(voteRequest);
        VoteResponse voteResponse = voteMapper.toVoteResponse(newVite);
        return new ResponseEntity<>(voteResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Get votes by quote", description = "Returns all the votes that the quote has")
    @GetMapping
    public ResponseEntity<List<VoteResponse>> getVotesByQuote(long quoteId) {
        List<Vote> votes = voteService.getVotesByQuoteId(quoteId);
        List<VoteResponse> voteResponses = voteMapper.toVoteResponse(votes);
        return new ResponseEntity<>(voteResponses, HttpStatus.FOUND);

    }
}
