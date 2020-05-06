package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

@Component
public class VoteFormConverter extends AbstractFormConverter<VoteForm, Vote> {

    @Autowired
    private VoteService voteService;

    @Override
    public Vote convert(VoteForm source) {
        Vote target = source.getVote();
        if (target == null) {
            Member member = SystemUser.getMember();
            target = simpleMapping(source, Vote.class);
            target.setVoteUuid(UUID.randomUUID().toString());
            target.setVoteNo(voteService.generateFunctionNo());
            target.setStatus(true);
            target.setCreator(member);
        } else {
            target.setContent(source.getContent());
            target.setVoteName(source.getVoteName());
            target.setMultiSelection(source.getMultiSelection());
            target.setExpiredDate(source.getExpiredDate());
            target.setIsAllowAdd(source.getIsAllowAdd());
            target.setIsOpen(source.getIsOpen());
            target.setIsSign(source.getIsSign());
            target.setUpdateDate(new Date());
        }

        return target;
    }
}
