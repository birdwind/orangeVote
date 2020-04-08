package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.view.vote.VoteListItem;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class VoteListItemConverter extends AbstractListConverter<Vote, VoteListItem> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

    @Autowired
    private VoteOptionService voteOptionService;

    @Override
    public Serializable getText(Vote source) {
        return source.getVoteName();
    }

    @Override
    public Serializable getValue(Vote source) {
        return source.getVoteUuid();
    }

    @Override
    public void setOtherProperty(VoteListItem item, Vote source) {
        item.setContent(source.getContent());
        item.setMultiSelection(source.getMultiSelection());
        item.setOption(voteOptionListItemConverter.convert(source.getVoteOptions()));
        item.setIsSign(source.getIsSign());
        item.setIsAllowAdd(source.getIsAllowAdd());
    }
}
