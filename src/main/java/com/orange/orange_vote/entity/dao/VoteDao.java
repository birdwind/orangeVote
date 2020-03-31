package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.Vote;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VoteDao extends BaseRepository<Vote, Integer> {

    @Query(value = "SELECT v FROM Vote v JOIN VoteTeamRelate vtr ON vtr.vote = v "
        + "JOIN MemberTeamRealte mtr ON mtr.team = vtr.team "
        + "WHERE v.status = true AND mtr.member.memberId = ?1 AND v.expiredDate > ?2")
    Optional<List<Vote>> findVotesByMemberId(Integer memberId, Date expiredDate);

}
