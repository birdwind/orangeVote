package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.base.dto.mapper.provider.PrimitiveProvider;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.view.vote.VoteDetailView;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionDetailListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;

@Component
public class VoteDetailViewConverter extends AbstractViewConverter<Vote, VoteDetailView> {

    private Member member;

    @Autowired
    private VoteOptionDetailListItemConverter voteOptionDetailListItemConverter;

    private PrimitiveProvider<Vote> voteOptionsProvider =
        (source, field) -> PrimitiveProvider.cast(voteOptionDetailListItemConverter.convert(source.getVoteOptions()));

    private PrimitiveProvider<Vote> isVotedProvider =
        (source,
            field) -> source.getVoteOptions().stream().map(VoteOption::getMemberVoteOptionRelates)
                .flatMap(Collection::stream)
                .filter(memberVoteOptionRelate -> memberVoteOptionRelate.getMember().getMemberId()
                    .equals(member.getMemberId()) && memberVoteOptionRelate.getStatus())
                .map(MemberVoteOptionRelate::getStatus).count() > 0;

    private PrimitiveProvider<Vote> isEndProvider = (source, field) -> source.getExpiredDate().before(new Date());

    private PrimitiveProvider<Vote> isOwnerProvider =
        (source, targetField) -> source.getCreator().getMemberId().equals(member.getMemberId());

    @Override
    public VoteDetailView convert(Vote source) {
        member = SystemUser.getMember();
        addValueProvider("options", voteOptionsProvider);
        addValueProvider("isVoted", isVotedProvider);
        addValueProvider("isEnd", isEndProvider);
        addValueProvider("isOwner", isOwnerProvider);
        return complexMapping(source, VoteDetailView.class);
    }
}
