package com.cameleoon.quotes.services;

import com.cameleoon.quotes.dto.mapers.QuoteMapper;
import com.cameleoon.quotes.dto.requests.QuoteRequest;
import com.cameleoon.quotes.exceptions.QuoteNotFoundException;
import com.cameleoon.quotes.exceptions.UserNotFoundException;
import com.cameleoon.quotes.models.Quote;
import com.cameleoon.quotes.models.User;
import com.cameleoon.quotes.repositories.QuoteRepository;
import com.cameleoon.quotes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final QuoteMapper quoteMapper;

    public Quote createQuote(QuoteRequest quoteRequest) {
        Quote newQuote = quoteMapper.toQuote(quoteRequest);
        User user = userRepository.findById(quoteRequest.getAuthorId()).orElseThrow(
                () -> new UserNotFoundException("Can't create quote with author id " + quoteRequest.getAuthorId()));
        newQuote.setQuoteId(null);
        newQuote.setAuthor(user);
        newQuote.setCreationTime(LocalDateTime.now());
        return quoteRepository.save(newQuote);

    }

    public Quote updateQuote(QuoteRequest quoteRequest) {
        Quote quote = quoteRepository.findById(quoteRequest.getQuoteId()).orElseThrow(
                () -> new QuoteNotFoundException("Can't update quote with id " + quoteRequest.getQuoteId()));
        User user = userRepository.findById(quoteRequest.getAuthorId()).orElseThrow(
                () -> new UserNotFoundException("Can't update quote with author id " + quoteRequest.getAuthorId()));
        quote.setAuthor(user);
        quote.setCreationTime(LocalDateTime.now());
        return quoteRepository.save(quote);

    }

    public void removeQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public List<Quote> getBestQuotes(int count) {
        PageRequest pageable = PageRequest.of(0, count);
        return quoteRepository.findBestQuotesByVotes(pageable);
    }

    public List<Quote> getWorseQuotes(int count) {
        PageRequest pageable = PageRequest.of(0, count);
        return quoteRepository.findWorseQuotesByVotes(pageable);
    }

    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuote();
    }
}
