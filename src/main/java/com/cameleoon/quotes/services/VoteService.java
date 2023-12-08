package com.cameleoon.quotes.services;

import com.cameleoon.quotes.dto.mapers.VoteMapper;
import com.cameleoon.quotes.dto.requests.VoteRequest;
import com.cameleoon.quotes.exceptions.MultipleVotingException;
import com.cameleoon.quotes.exceptions.QuoteNotFoundException;
import com.cameleoon.quotes.exceptions.UserNotFoundException;
import com.cameleoon.quotes.models.Quote;
import com.cameleoon.quotes.models.User;
import com.cameleoon.quotes.models.Vote;
import com.cameleoon.quotes.repositories.QuoteRepository;
import com.cameleoon.quotes.repositories.UserRepository;
import com.cameleoon.quotes.repositories.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;
    private final VoteMapper voteMapper;

    public Vote saveVote(VoteRequest voteRequest) {

        User user = userRepository.findById(voteRequest.getUserId()).orElseThrow(
                () -> new UserNotFoundException("Cannot create a vote for the user with id " + voteRequest.getUserId())
        );

        Quote quote = quoteRepository.findById(voteRequest.getQuoteId()).orElseThrow(
                () -> new QuoteNotFoundException("Cannot create a vote for the quote with id " + voteRequest.getQuoteId())
        );

        if (voteRepository.existsByUserAndQuote(user, quote)) {
            throw new MultipleVotingException(String.format(
                    "user with id %d has already voted for the quote with id %d",
                    voteRequest.getUserId(), voteRequest.getQuoteId()));
        }

        Vote newVote = voteMapper.toVote(voteRequest);
        newVote.setUser(user);
        newVote.setQuote(quote);
        return voteRepository.save(newVote);
    }

    public List<Vote> getVotesByQuoteId(long quoteId) {
        return voteRepository.getVotesByQuoteId(quoteId);
    }
}
