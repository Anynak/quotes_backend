package com.cameleoon.quotes.dto.mapers;

import com.cameleoon.quotes.dto.requests.VoteRequest;
import com.cameleoon.quotes.dto.responses.VoteResponse;
import com.cameleoon.quotes.models.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VoteMapper {
    @Mapping(source = "vote.quote.quoteId", target = "quoteId")
    @Mapping(source = "vote.user.userId", target = "userId")
    VoteResponse toVoteResponse(Vote vote);

    List<VoteResponse> toVoteResponse(List<Vote> votes);

    Vote toVote(VoteRequest voteRequest);
}
