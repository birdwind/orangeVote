package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.VoteOption;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.Optional;

public interface VoteOptionDao extends BaseRepository<VoteOption, Integer> {

    @Query(value = "SELECT vo FROM VoteOption vo JOIN MemberVoteOptionRelate mvor "
        + "ON mvor.voteOption.voteOptionId = vo.voteOptionId AND mvor.member.memberId = ?3 "
        + "WHERE vo.vote.voteId = ?1 AND vo.voteOptionId = ?2 AND vo.status = true")
    VoteOption findVoteOptionBeSelect(Integer voteId, Integer voteOptionId, Integer memberId);

    @Query(
        value = "SELECT vo FROM VoteOption vo WHERE vo.status = true AND vo.voteOptionUuid = ?1 AND vo.vote.voteId = ?2")
    Optional<VoteOption> findVoteOptionByVoteOptionUuidAndVoteId(String voteOptionUuid, Integer voteId);

    @Query(value = "SELECT COUNT(mvor) FROM MemberVoteOptionRelate mvor "
        + "JOIN VoteOption vo ON vo = mvor.voteOption WHERE vo.vote.voteId = ?1 AND mvor.status = true")
    BigDecimal countOptionBeSelectedByVoteId(Integer voteId);
}
