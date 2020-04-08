package com.orange.orange_vote.aspect;

import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.MemberErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.member.MemberCreateForm;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class MemberFormAspect extends CreateUpdateFormAspect<MemberCreateForm, MemberUpdateForm> {

    @Autowired
    private MemberService memberService;

    @Override
    protected void putAuthenticate(MemberCreateForm form, BindingResult errors) throws EntityNotFoundException {

    }

    @Override
    protected void postAuthenticate(MemberUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = memberService.getMemberByMemberUuid(form.getMemberUuid())
            .orElseThrow(() -> new EntityNotFoundException("memberUuid", MemberErrorConstants.MEMBER_NOT_FOUND));

        form.setMember(member);

    }
}
