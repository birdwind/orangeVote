package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.base.utils.DateTimeUtils;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.service.VoteOptionService;
import com.orange.orange_vote.view.vote.VoteListItem;
import com.orange.orange_vote.view.vote.VotedListItem;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionDetailListItemConverter;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Component
public class VotedListItemConverter extends AbstractListConverter<Vote, VotedListItem> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

    @Autowired
    private VoteOptionDetailListItemConverter voteOptionDetailListItemConverter;

    @Override
    public Serializable getText(Vote source) {
        return source.getVoteName();
    }

    @Override
    public Serializable getValue(Vote source) {
        return source.getVoteUuid();
    }

    @Override
    public void setOtherProperty(VotedListItem item, Vote source) {
        item.setContent(source.getContent());
        item.setMultiSelection(source.getMultiSelection());
        item.setTeam(source.getVoteTeamRelates().getTeam().getTeamValue());
        item.setExpireDate(DateTimeUtils.dateFormat(source.getExpiredDate()));
        item.setIsSign(source.getIsSign());
        item.setIsAllowAdd(source.getIsAllowAdd());
        if(source.getExpiredDate().before(new Date())){
            item.setIsEnd(true);
            item.setOptionsDetail(voteOptionDetailListItemConverter.convert(source.getVoteOptions()));
        }else {
            item.setIsEnd(false);
            item.setOptions(voteOptionListItemConverter.convert(source.getVoteOptions()));
        }

        Member member = SystemUser.getMember();
        item.setIsVoted(source.getVoteOptions().stream()
                .map(VoteOption::getMemberVoteOptionRelates).flatMap(Collection::stream)
                .filter(memberVoteOptionRelate -> memberVoteOptionRelate.getMember().getMemberId()
                        .equals(member.getMemberId()) && memberVoteOptionRelate.getStatus())
                .map(MemberVoteOptionRelate::getStatus).count() > 0);
    }
}
