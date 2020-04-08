package com.orange.orange_vote.view.voteOption.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberVoteOptionRelate;
import com.orange.orange_vote.view.voteOption.MemberVoteOptionRelateForm;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberVoteOptionRelateFormConverter
    extends AbstractFormConverter<MemberVoteOptionRelateForm, MemberVoteOptionRelate> {

    @Override
    public MemberVoteOptionRelate convert(MemberVoteOptionRelateForm source) {
        return null;
    }

    public List<MemberVoteOptionRelate> convertToList(MemberVoteOptionRelateForm source) {
        Member member = SystemUser.getMember();
        return source.getVoteOptions().stream().map(voteOption -> new MemberVoteOptionRelate(member, voteOption))
            .collect(Collectors.toList());
    }
}
