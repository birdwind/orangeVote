package com.orange.orange_vote.entity.service;

import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface VoteOptionService {

    Boolean getVoteOptionBeSelect(Integer voteId, Integer voteOptionId);

    Optional<VoteOption> getVoteOptionByVoteOptionUuidAndVote(String voteOptionUuid, Vote vote);

    List<VoteOption> saveAll(Collection<VoteOption> voteOptions);

    List<VoteOption> deleteAll(Collection<VoteOption> voteOptions);

}
