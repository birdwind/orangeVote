package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.dao.VoteOptionDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.VoteOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class VoteOptionServiceImpl implements VoteOptionService {

    @Autowired
    private VoteOptionDao voteOptionDao;

    @Override
    public Boolean getVoteOptionBeSelect(Integer voteId, Integer voteOptionId) {
        Member member = SystemUser.getMember();
        return voteOptionDao.findVoteOptionBeSelect(voteId, voteOptionId, member.getMemberId()) != null;
    }
}
