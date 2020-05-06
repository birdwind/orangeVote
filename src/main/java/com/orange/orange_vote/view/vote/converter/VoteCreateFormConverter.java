package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteCreateFormConverter extends AbstractFormConverter<VoteCreateForm, Vote> {

    @Autowired
    private VoteService voteService;

    @Override
    public Vote convert(VoteCreateForm source) {
        Member member = SystemUser.getMember();

        return simpleMapping(source, new Vote(member, voteService.generateFunctionNo()));
    }
}
