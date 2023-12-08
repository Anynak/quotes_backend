package com.cameleoon.quotes.repositories;

import com.cameleoon.quotes.models.Quote;
import com.cameleoon.quotes.models.User;
import com.cameleoon.quotes.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "select v from Vote v join v.quote q where q.quoteId=?1")
    List<Vote> getVotesByQuoteId(Long id);

    boolean existsByUserAndQuote(User user, Quote quote);
}
