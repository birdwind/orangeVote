package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface MemberVoteOptionRelateDao extends BaseRepository<MemberVoteOptionRelate, Integer> {

    @Query(value = "SELECT mvor FROM MemberVoteOptionRelate mvor "
        + "JOIN VoteOption vo ON vo.voteOptionId = mvor.voteOption.voteOptionId WHERE vo.vote.voteId = ?1")
    Optional<List<MemberVoteOptionRelate>> findVoteAlreadyByVoteId(Integer voteId);

}
