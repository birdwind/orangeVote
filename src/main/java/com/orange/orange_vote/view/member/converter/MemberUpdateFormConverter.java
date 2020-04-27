package com.orange.orange_vote.view.member.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class MemberUpdateFormConverter extends AbstractFormConverter<MemberUpdateForm, Member> {

    private Member member;

    @Override
    public Member convert(MemberUpdateForm source) {
        Member target = simpleMapping(source, member != null ? member : source.getMember());
        target.setUpdateDate(new Date());
//        if (source.getModifyPassword() != null) {
//            target.encodePasssword(source.getModifyPassword());
//        }
        return target;
    }

    public Member convert(MemberUpdateForm source, Member member) {
        this.member = member;
        return convert(source);
    }
}
