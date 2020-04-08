package com.orange.orange_vote.view.member.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.member.MemberCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberCreateFormConverter extends AbstractFormConverter<MemberCreateForm, Member> {

    @Autowired
    private MemberService memberService;

    @Override
    public Member convert(MemberCreateForm source) {
        Member member = simpleMapping(source, Member.class);
        member.encodePasssword(source.getPassword());
        member.setMemberNo(memberService.generateMemberNo());
        return member;
    }
}
