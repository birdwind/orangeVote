package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.vote.VoteView;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class VoteViewConverter extends AbstractViewConverter<Vote, VoteView> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

    private PrimitiveProvider<Vote> voteOptionsProvider =
        (source, field) -> PrimitiveProvider.cast(voteOptionListItemConverter
            .convert(source.getVoteOptions().stream().filter(VoteOption::getStatus).collect(Collectors.toList())));

    @Override
    public VoteView convert(Vote source) {
        addValueProvider("voteOptions", voteOptionsProvider);
        return complexMapping(source, VoteView.class);
    }
}
