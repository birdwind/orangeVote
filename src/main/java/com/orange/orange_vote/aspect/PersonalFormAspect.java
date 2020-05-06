package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.UpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.constans.MemberErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.personal.PersonalUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class PersonalFormAspect extends UpdateFormAspect<PersonalUpdateForm> {

    @Autowired
    private MemberService memberService;

    @Override
    protected void postAuthenticate(PersonalUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        Member loginMember = SystemUser.getMember();
        Member member = memberService.getMemberByUsername(form.getUsername()).orElse(null);
        if (member != null && !member.getMemberId().equals(loginMember.getMemberId())) {
            errors.rejectValue("username", MemberErrorConstantsEnums.MEMBER_USERNAME_DUPLICATE.valueOfCode(),
                MemberErrorConstantsEnums.MEMBER_USERNAME_DUPLICATE.valueOfName());
        }
    }
}
