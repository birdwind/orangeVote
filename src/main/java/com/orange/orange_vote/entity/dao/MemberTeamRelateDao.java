package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.MemberTeamRealte;
import org.springframework.data.jpa.repository.Query;

public interface MemberTeamRelateDao extends BaseRepository<MemberTeamRealte, Integer> {

    @Query(value = "SELECT mtr FROM MemberTeamRealte mtr " +
            "WHERE mtr.member.memberId = ?1 AND mtr.team.teamId = ?2 AND mtr.status = true ")
    MemberTeamRealte checkExistRelate(Integer memberId, Integer teamId);

}
