package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.MemberErrorConstants;
import com.orange.orange_vote.constans.RoleErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.entity.service.RoleService;
import com.orange.orange_vote.view.member.MemberRoleRelateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MemberRoleRelateFormAspect extends CreateUpdateFormAspect<MemberRoleRelateForm, MemberRoleRelateForm> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberService memberService;

    private AtomicInteger index = new AtomicInteger();

    @Override
    protected void putAuthenticate(MemberRoleRelateForm form, BindingResult errors) throws EntityNotFoundException {
        authenticate(form, errors, null);
    }

    @Override
    protected void postAuthenticate(MemberRoleRelateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = memberService.getMemberByMemberUuid(form.getMemberUuid())
            .orElseThrow(() -> new EntityNotFoundException("memberUuid", MemberErrorConstants.MEMBER_NOT_FOUND));
        authenticate(form, errors, member);
        List<MemberRoleRelate> memberRoleRelates = Lists.newArrayList();
        index.set(0);
        form.getDeleteRoleUuids().forEach(roleUuid -> {
            int i = index.getAndIncrement();
            MemberRoleRelate memberRoleRelate =
                roleService.getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
            if (memberRoleRelate == null) {
                errors.rejectValue("deleteRoleUuids[" + i + "]", RoleErrorConstants.ROLE_NOTFOUND);
            } else {
                memberRoleRelates.add(memberRoleRelate);
            }
        });

        form.setDeleteRoles(memberRoleRelates);
    }

    private void authenticate(MemberRoleRelateForm form, BindingResult errors, Member member) {
        List<Role> roles = Lists.newArrayList();
        index.set(0);
        form.getRoleUuids().forEach(roleUuid -> {
            int i = index.getAndIncrement();
            Role role = roleService.getRoleByRoleUuid(roleUuid).orElse(null);
            if (member != null) {
                MemberRoleRelate memberRoleRelate =
                    roleService.getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
                if (memberRoleRelate != null) {
                    errors.rejectValue("roleUuids[" + i + "]", RoleErrorConstants.MEMBER_ROLE_DUPLICATE);
                }
            }
            if (role == null) {
                errors.rejectValue("roleUuids[" + i + "]", RoleErrorConstants.ROLE_NOTFOUND);
            } else {
                roles.add(role);
            }
        });

        form.setRoles(roles);
    }
}
