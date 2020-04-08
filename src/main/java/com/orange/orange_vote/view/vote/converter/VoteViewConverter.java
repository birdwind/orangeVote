package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.view.vote.VoteView;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteViewConverter extends AbstractViewConverter<Vote, VoteView> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

    @Override
    public VoteView convert(Vote source) {
        VoteView voteView = complexMapping(source, VoteView.class);
        voteView.setVoteOptions(voteOptionListItemConverter.convert(source.getAddVoteOptions()));
        return voteView;
    }
}
