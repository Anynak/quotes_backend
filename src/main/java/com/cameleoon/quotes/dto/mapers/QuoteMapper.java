package com.cameleoon.quotes.dto.mapers;

import com.cameleoon.quotes.dto.requests.QuoteRequest;
import com.cameleoon.quotes.dto.responses.QuoteResponse;
import com.cameleoon.quotes.models.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuoteMapper {
    QuoteResponse toQuoteResponse(Quote quote);

    List<QuoteResponse> toQuoteResponse(List<Quote> quotes);

    Quote toQuote(QuoteRequest quoteRequest);
}
