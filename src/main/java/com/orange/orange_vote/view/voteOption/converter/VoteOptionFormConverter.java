package com.orange.orange_vote.view.voteOption.converter;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.voteOption.VoteOptionForm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class VoteOptionFormConverter extends AbstractFormConverter<VoteOptionForm, VoteOption> {

    private Vote vote;

    @Override
    public VoteOption convert(VoteOptionForm source) {
        VoteOption target = source.getVoteOption();
        if (target == null) {
            Member member = SystemUser.getMember();
            target = simpleMapping(source, VoteOption.class);
            target.setVote(vote);
//            target.setVoteOptionUuid(UUID.randomUUID().toString());
//            target.setStatus(true);
            target.setVoteOptionValue(source.getValue());
            target.setCreator(member);
            target.setCreateDate(new Date());
            target.setUpdateDate(new Date());
        } else {
            target = simpleMapping(source, target);
            target.setVoteOptionValue(source.getValue());
            target.setUpdateDate(new Date());
        }
        return target;
    }

    public List<VoteOption> convertList(List<VoteOptionForm> sources, Vote vote) {
        List<VoteOption> voteOptions = Lists.newArrayList();
        this.vote = vote;
        if (sources != null) {
            sources.forEach(source -> {
                voteOptions.add(convert(source));
            });
        }
        return voteOptions;
    }
}
