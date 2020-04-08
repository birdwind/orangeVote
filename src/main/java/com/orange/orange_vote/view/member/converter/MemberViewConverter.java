package com.orange.orange_vote.view.member.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractViewConverter;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.view.member.MemberView;
import org.springframework.stereotype.Component;

@Component
public class MemberViewConverter extends AbstractViewConverter<Member, MemberView> {

    @Override
    public MemberView convert(Member source) {
        return complexMapping(source, MemberView.class);
    }
}
