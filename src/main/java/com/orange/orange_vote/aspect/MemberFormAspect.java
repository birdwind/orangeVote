package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateUpdateDeleteFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.MemberErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.member.MemberCreateForm;
import com.orange.orange_vote.view.member.MemberDeleteForm;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MemberFormAspect
    extends CreateUpdateDeleteFormAspect<MemberCreateForm, MemberUpdateForm, MemberDeleteForm> {

    @Autowired
    private MemberService memberService;

    @Override
    protected void putAuthenticate(MemberCreateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = memberService.getMemberByOrangeId(form.getOrangeId()).orElse(null);
        if (member != null) {
            errors.rejectValue("orangeId", MemberErrorConstantsEnums.MEMBER_ORANGEID_DUPLICATE.valueOfCode(),
                MemberErrorConstantsEnums.MEMBER_ORANGEID_DUPLICATE.valueOfName());
        }
    }

    @Override
    protected void postAuthenticate(MemberUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = memberService.getMemberByMemberUuid(form.getMemberUuid())
            .orElseThrow(() -> new EntityNotFoundException("memberUuid",
                MemberErrorConstantsEnums.MEMBER_NOT_FOUND.valueOfCode(),
                MemberErrorConstantsEnums.MEMBER_NOT_FOUND.valueOfName()));
        form.setMember(member);

        Member memberTemp = memberService.getMemberByUsername(form.getUsername()).orElse(null);
        if (memberTemp != null && !memberTemp.getMemberId().equals(member.getMemberId())) {
            errors.rejectValue("username", MemberErrorConstantsEnums.MEMBER_USERNAME_DUPLICATE.valueOfCode(),
                MemberErrorConstantsEnums.MEMBER_USERNAME_DUPLICATE.valueOfName());
        }

    }

    @Override
    protected void deleteAuthenticate(MemberDeleteForm form, BindingResult errors) throws EntityNotFoundException {
        List<Member> members = Lists.newArrayList();
        AtomicInteger index = new AtomicInteger();
        form.getMemberUuids().forEach(memberUuid -> {
            int i = index.getAndIncrement();
            Member member = memberService.getMemberByMemberUuid(memberUuid).orElse(null);
            if (member == null) {
                errors.rejectValue("memberUuids[" + i + "]", MemberErrorConstantsEnums.MEMBER_NOT_FOUND.valueOfCode(),
                    MemberErrorConstantsEnums.MEMBER_NOT_FOUND.valueOfName());
            } else {
                members.add(member);
            }
        });

        form.setMembers(members);
    }
}
