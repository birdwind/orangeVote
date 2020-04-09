package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import java.util.List;
import java.util.Optional;

public interface VoteService {

    String generateFunctionNo();

    List<Vote> getAllVotesByMemberId(Integer memberId);

    List<Vote> getAllVotesByMemberIdAndIsOpen(Integer memberId);

    Optional<Vote> getVoteByVoteUuidAndCreatorId(String voteUuid, Integer memberId);

    Vote saveVote(Vote vote, Team team);

    Vote updateVote(Vote vote, Team team);

    Optional<Vote> getVoteByVoteUuidAndMemberId(String voteUuid, Integer memberId);

}
