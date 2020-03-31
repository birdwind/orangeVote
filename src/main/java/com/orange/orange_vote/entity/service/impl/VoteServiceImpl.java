package com.orange.orange_vote.entity.service.impl;

import com.google.common.collect.Lists;
import com.orange.orange_vote.entity.dao.VoteDao;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao voteDao;

    @Override
    public List<Vote> getAllVotesByMemberId(Integer memberId) {
        return voteDao.findVotesByMemberId(memberId, new Date()).orElse(Lists.newArrayList());
    }
}
