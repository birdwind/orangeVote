package com.orange.orange_vote.view.voteOption.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.view.voteOption.VoteOptionListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class VoteOptionListItemConverter extends AbstractListConverter<VoteOption, VoteOptionListItem> {

    @Autowired
    private VoteOptionService voteOptionService;

    @Override
    public Serializable getText(VoteOption source) {
        return source.getVoteOptionValue();
    }

    @Override
    public Serializable getValue(VoteOption source) {
        return source.getVoteOptionUuid();
    }

    @Override
    public void setOtherProperty(VoteOptionListItem item, VoteOption source) {
        item.setSelect(voteOptionService.getVoteOptionBeSelect(source.getVote().getVoteId(), source.getVoteOptionId()));
    }
}
