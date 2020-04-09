package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteTeamRelate;
import com.orange.orange_vote.view.team.converter.TeamListItemConverter;
import com.orange.orange_vote.view.vote.VoteDetailView;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionDetailListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class VoteDetailViewConverter extends AbstractViewConverter<Vote, VoteDetailView> {

    @Autowired
    private VoteOptionDetailListItemConverter voteOptionDetailListItemConverter;

    @Autowired
    private TeamListItemConverter teamListItemConverter;

    private PrimitiveProvider<Vote> voteOptionsProvider =
        (source, field) -> PrimitiveProvider.cast(voteOptionDetailListItemConverter.convert(source.getVoteOptions()));

    private PrimitiveProvider<Vote> teamsProvider = (source, field) -> PrimitiveProvider.cast(teamListItemConverter
        .convert(source.getVoteTeamRelates().stream().map(VoteTeamRelate::getTeam).collect(Collectors.toList())));

    @Override
    public VoteDetailView convert(Vote source) {
        addValueProvider("optionDetailViews", voteOptionsProvider);
        addValueProvider("teams", teamsProvider);
        return complexMapping(source, VoteDetailView.class);
    }
}
