package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.vote.VoteUpdateForm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoteUpdateFormConverter extends AbstractFormConverter<VoteUpdateForm, Vote> {

    @Override
    public Vote convert(VoteUpdateForm source) {
        Member creator = SystemUser.getMember();
        Vote target = simpleMapping(source, source.getVote());
        target.setUpdateDate(new Date());

        List<VoteOption> voteOptionList =
            source.getDeleteVoteOptionList().stream().peek(BaseModel::delete).collect(Collectors.toList());
        voteOptionList.addAll(source.getUpdateVoteOptionList());

        voteOptionList.addAll(source.getAddOptionValues().stream()
            .map(voteOptionValue -> new VoteOption(creator, voteOptionValue, target)).collect(Collectors.toList()));

        target.setUpdateVoteOptions(voteOptionList);
        return target;
    }
}
