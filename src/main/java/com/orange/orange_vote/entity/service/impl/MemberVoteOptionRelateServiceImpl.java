package com.orange.orange_vote.entity.service.impl;

import com.orange.orange_vote.entity.dao.MemberVoteOptionRelateDao;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.service.MemberVoteOptionRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberVoteOptionRelateServiceImpl implements MemberVoteOptionRelateService {

    @Autowired
    private MemberVoteOptionRelateDao memberVoteOptionRelateDao;

    @Override
    public List<MemberVoteOptionRelate> saveAll(Collection<MemberVoteOptionRelate> memberVoteOptionRelates) {
        return memberVoteOptionRelateDao.saveAll(memberVoteOptionRelates);
    }

    @Override
    public Boolean isVoteAlready(Integer voteId, Member member) {
        List<MemberVoteOptionRelate> memberVoteOptionRelates =
            memberVoteOptionRelateDao.findVoteAlreadyByVoteId(voteId, member).orElse(null);
        return memberVoteOptionRelates != null;
    }
}
