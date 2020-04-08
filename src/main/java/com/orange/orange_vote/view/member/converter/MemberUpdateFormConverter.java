package com.orange.orange_vote.view.member.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class MemberUpdateFormConverter extends AbstractFormConverter<MemberUpdateForm, Member> {

    @Override
    public Member convert(MemberUpdateForm source) {
        Member target = simpleMapping(source, source.getMember());
        target.setUpdateDate(new Date());
        if (source.getModifyPassword() != null) {
            target.encodePasssword(source.getModifyPassword());
        }
        return target;
    }
}
