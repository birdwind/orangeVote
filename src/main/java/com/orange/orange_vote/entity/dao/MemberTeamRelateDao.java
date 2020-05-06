package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.MemberTeamRelate;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamRelateDao extends BaseRepository<MemberTeamRelate, Integer> {

    @Query(value = "SELECT mtr FROM MemberTeamRelate mtr " +
            "WHERE mtr.member.memberId = ?1 AND mtr.team.teamId = ?2 AND mtr.status = true ")
    MemberTeamRelate checkExistRelate(Integer memberId, Integer teamId);

}
