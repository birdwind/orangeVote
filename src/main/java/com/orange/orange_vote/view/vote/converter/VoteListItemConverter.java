package com.orange.orange_vote.view.vote.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractListConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberTeamRelate;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.entity.model.Team;
import com.orange.orange_vote.entity.model.Vote;
import com.orange.orange_vote.entity.model.VoteOption;
import com.orange.orange_vote.entity.model.VoteTeamRelate;
import com.orange.orange_vote.view.vote.VoteListItem;
import com.orange.orange_vote.view.voteOption.converter.VoteOptionListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.stream.Collectors;

@Component
public class VoteListItemConverter extends AbstractListConverter<Vote, VoteListItem> {

    @Autowired
    private VoteOptionListItemConverter voteOptionListItemConverter;

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
        Member loginMember = SystemUser.getMember();
        item.setContent(source.getContent());
        item.setMultiSelection(source.getMultiSelection());
        item.setOption(voteOptionListItemConverter.convert(source.getVoteOptions()));
        item.setIsSign(source.getIsSign());
        item.setIsAllowAdd(source.getIsAllowAdd());
        item.setIsVoted(
            source.getVoteOptions().stream().map(VoteOption::getMemberVoteOptionRelates)
                .filter(memberVoteOptionRelates -> memberVoteOptionRelates
                    .stream().filter(MemberVoteOptionRelate::getStatus).map(MemberVoteOptionRelate::getMember)
                    .anyMatch(member -> member.equalsTo(loginMember)))
                .flatMap(
                    memberVoteOptionRelates -> memberVoteOptionRelates.stream().map(MemberVoteOptionRelate::getMember))
                .findFirst().isPresent());
        item.setTeam(
            source.getVoteTeamRelates().stream().map(VoteTeamRelate::getTeam).map(Team::getMemberTeamRelateList)
                .filter(memberTeamRealtes -> memberTeamRealtes.stream().map(MemberTeamRelate::getMember)
                    .anyMatch(member -> member.equalsTo(loginMember)))
                .flatMap(memberTeamRealtes -> memberTeamRealtes.stream().map(MemberTeamRelate::getTeam))
                .map(Team::getTeamValue).collect(Collectors.toSet()).stream().reduce((a, b) -> a + "," + b).orElse(""));
    }
}
