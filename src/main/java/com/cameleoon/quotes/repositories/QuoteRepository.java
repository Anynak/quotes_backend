package com.cameleoon.quotes.repositories;

import com.cameleoon.quotes.models.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("SELECT q FROM Quote q JOIN Vote v ON q.quoteId = v.quote.quoteId " +
            "GROUP BY q.quoteId ORDER BY SUM(v.voteValue) DESC")
    List<Quote> findBestQuotesByVotes(Pageable pageable);

    @Query("SELECT q FROM Quote q JOIN Vote v ON q.quoteId = v.quote.quoteId " +
            "GROUP BY q.quoteId ORDER BY SUM(v.voteValue)")
    List<Quote> findWorseQuotesByVotes(Pageable pageable);

    @Query(value = "SELECT * FROM QUOTES.QUOTE ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();
}
