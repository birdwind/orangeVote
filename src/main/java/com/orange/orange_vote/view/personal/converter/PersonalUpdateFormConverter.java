package com.orange.orange_vote.view.personal.converter;

import com.orange.orange_vote.base.dto.mapper.converter.abstracts.AbstractFormConverter;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.view.personal.PersonalUpdateForm;
import org.springframework.stereotype.Component;

@Component
public class PersonalUpdateFormConverter extends AbstractFormConverter<PersonalUpdateForm, Member> {

    @Override
    public Member convert(PersonalUpdateForm source) {
        Member member = SystemUser.getMember();
        return simpleMapping(source, member);
    }
}
