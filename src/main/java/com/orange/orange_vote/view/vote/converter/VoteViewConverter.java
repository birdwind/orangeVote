package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberTeamRelate;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.model.VoteTeamRelate;
import com.orange.orange_vote.view.vote.VoteView;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class VoteViewConverter extends AbstractViewConverter<Vote, VoteView> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

    private Member member;

    private PrimitiveProvider<Vote> voteOptionsProvider =
        (source, field) -> PrimitiveProvider.cast(voteOptionListItemConverter
            .convert(source.getVoteOptions().stream().filter(VoteOption::getStatus).collect(Collectors.toList())));

    private PrimitiveProvider<Vote> isVotedProvider =
        (source,
            field) -> source.getVoteOptions().stream()
                .map(VoteOption::getMemberVoteOptionRelates).flatMap(Collection::stream)
                .filter(memberVoteOptionRelate -> memberVoteOptionRelate.getMember().getMemberId()
                    .equals(member.getMemberId()) && memberVoteOptionRelate.getStatus())
                .map(MemberVoteOptionRelate::getStatus).count();

    private PrimitiveProvider<Vote> teamProvider =
        (source, targetField) -> source.getVoteTeamRelates().stream().filter(Objects::nonNull)
            .map(VoteTeamRelate::getTeam).map(Team::getMemberTeamRelateList).flatMap(Collection::stream)
            .filter(memberTeamRelate -> memberTeamRelate.getMember().getMemberId().equals(member.getMemberId()))
            .map(MemberTeamRelate::getTeam).map(Team::getTeamValue).reduce((a, b) -> a + "," + b).orElse("");

    @Override
    public VoteView convert(Vote source) {
        member = SystemUser.getMember();
        addValueProvider("voteOptions", voteOptionsProvider);
        addValueProvider("isVoted", isVotedProvider);
        addValueProvider("team", teamProvider);
        return complexMapping(source, VoteView.class);
    }

    public List<VoteView> convert(List<Vote> sources, Member member) {
        return sources.stream().filter(Objects::nonNull).map(this::convert).filter(Objects::nonNull)
            .collect(Collectors.toList());
    }
}
