package com.orange.orange_vote.entity.dao;

import com.orange.orange_vote.base.repo.BaseRepository;
import com.orange.orange_vote.entity.model.MemberTeamRealte;
import com.orange.orange_vote.entity.model.VoteTeamRelate;
import org.springframework.data.jpa.repository.Query;

public interface VoteTeamRelateDao extends BaseRepository<VoteTeamRelate, Integer> {

}
