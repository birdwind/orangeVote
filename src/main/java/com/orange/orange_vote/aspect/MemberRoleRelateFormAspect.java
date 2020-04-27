package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateUpdateFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.MemberErrorConstantsEnums;
import com.orange.orange_vote.constans.RoleErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.entity.service.MemberRoleRelateService;
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

    @Autowired
    private MemberRoleRelateService memberRoleRelateService;

    private AtomicInteger index = new AtomicInteger();

    @Override
    protected void putAuthenticate(MemberRoleRelateForm form, BindingResult errors) throws EntityNotFoundException {
        authenticate(form, errors, null);
    }

    @Override
    protected void postAuthenticate(MemberRoleRelateForm form, BindingResult errors) throws EntityNotFoundException {
        Member member = memberService.getMemberByMemberUuid(form.getRelateUuid()).orElseThrow(
            () -> new EntityNotFoundException("relateUuid", MemberErrorConstantsEnums.MEMBER_NOT_FOUND.valueOfName()));
        authenticate(form, errors, member);
        List<MemberRoleRelate> memberRoleRelates = Lists.newArrayList();
        index.set(0);
        form.getDeleteRoleUuids().forEach(roleUuid -> {
            int i = index.getAndIncrement();
            MemberRoleRelate memberRoleRelate = memberRoleRelateService
                .getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
            if (memberRoleRelate == null) {
                errors.rejectValue("deleteRoleUuids[" + i + "]", RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfCode(),
                    RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfName());
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
                MemberRoleRelate memberRoleRelate = memberRoleRelateService
                    .getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
                if (memberRoleRelate != null) {
                    errors.rejectValue("roleUuids[" + i + "]",
                        RoleErrorConstantsEnums.MEMBER_ROLE_DUPLICATE.valueOfCode(),
                        RoleErrorConstantsEnums.MEMBER_ROLE_DUPLICATE.valueOfName());
                }
            }
            if (role == null) {
                errors.rejectValue("roleUuids[" + i + "]", RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfCode(),
                    RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfName());
            } else {
                roles.add(role);
            }
        });

        form.setRoles(roles);
    }
}
