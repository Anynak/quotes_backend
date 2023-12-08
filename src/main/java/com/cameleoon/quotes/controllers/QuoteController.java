package com.cameleoon.quotes.controllers;

import com.cameleoon.quotes.dto.mapers.QuoteMapper;
import com.cameleoon.quotes.dto.requests.QuoteRequest;
import com.cameleoon.quotes.dto.responses.QuoteResponse;
import com.cameleoon.quotes.models.Quote;
import com.cameleoon.quotes.services.QuoteService;
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
@RequestMapping("/quotes/v1")
@Validated
public class QuoteController {

    private final QuoteMapper quoteMapper;
    private final QuoteService quoteService;

    @Operation(summary = "Create a new quote", description = "Returns a new quote")
    @PostMapping
    public ResponseEntity<QuoteResponse> createQuote(@RequestBody @Valid QuoteRequest quoteRequest) {
        Quote newQuote = quoteService.createQuote(quoteRequest);
        QuoteResponse quoteResponse = quoteMapper.toQuoteResponse(newQuote);
        return new ResponseEntity<>(quoteResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Edit a quote", description = "the quote must contain an id")
    @PutMapping
    public ResponseEntity<QuoteResponse> editQuote(@RequestBody @Valid QuoteRequest quoteRequest) {
        Quote quote = quoteService.updateQuote(quoteRequest);
        QuoteResponse quoteResponse = quoteMapper.toQuoteResponse(quote);
        return new ResponseEntity<>(quoteResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a quote", description = "Deletes a quote as per the id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuote(@PathVariable Long id) {
        quoteService.removeQuote(id);
    }

    @Operation(summary = "Get random quote")
    @GetMapping("/random")
    public ResponseEntity<QuoteResponse> getRandomQuote() {
        Quote quote = quoteService.getRandomQuote();
        QuoteResponse quoteResponse = quoteMapper.toQuoteResponse(quote);
        return new ResponseEntity<>(quoteResponse, HttpStatus.FOUND);
    }

    @Operation(summary = "Get best quotes", description = "Returns best quotes. 'n' is the number of quotes")
    @GetMapping("/best")
    public ResponseEntity<List<QuoteResponse>> getBestQuotes(int n) {
        List<Quote> quotes = quoteService.getBestQuotes(n);
        List<QuoteResponse> quoteResponses = quoteMapper.toQuoteResponse(quotes);
        return new ResponseEntity<>(quoteResponses, HttpStatus.FOUND);
    }

    @Operation(summary = "Get worse quotes", description = "Returns worse quotes. 'n' is the number of quotes")
    @GetMapping("/worse")
    public ResponseEntity<List<QuoteResponse>> getWorseQuotes(int n) {
        List<Quote> quotes = quoteService.getWorseQuotes(n);
        List<QuoteResponse> quoteResponses = quoteMapper.toQuoteResponse(quotes);
        return new ResponseEntity<>(quoteResponses, HttpStatus.FOUND);
    }

}
