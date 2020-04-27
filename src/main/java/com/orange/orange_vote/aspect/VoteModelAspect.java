package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.AbstractAuthModelAspect;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteModelAspect extends AbstractAuthModelAspect<Vote> {

    @Autowired
    private VoteService voteService;

    @Override
    protected Vote authenticate(String source){
        Member member = SystemUser.getMember();
        return voteService.getVoteByVoteUuidAndMemberIdAndCreatorId(source, member.getMemberId()).orElse(null);
    }
}
