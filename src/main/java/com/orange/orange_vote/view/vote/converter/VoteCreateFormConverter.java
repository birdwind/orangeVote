package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.VoteService;
import com.orange.orange_vote.view.vote.VoteCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteCreateFormConverter extends AbstractFormConverter<VoteCreateForm, Vote> {

    @Autowired
    private VoteService voteService;

    @Override
    public Vote convert(VoteCreateForm source) {
        Member member = SystemUser.getMember();
        Vote target = simpleMapping(source, new Vote(member, voteService.generateFunctionNo()));
        List<VoteOption> voteOptionList = source.getOptionValues().stream()
            .map(optionValue -> new VoteOption(member, optionValue, target)).collect(Collectors.toList());
        target.setAddVoteOptions(voteOptionList);

        return target;
    }
}
