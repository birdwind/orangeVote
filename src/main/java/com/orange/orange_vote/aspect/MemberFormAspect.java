package com.orange.orange_vote.aspect;

import com.google.common.collect.Lists;
import com.orange.orange_vote.base.aop.CreateUpdateDeleteFormAspect;
import com.orange.orange_vote.base.exception.EntityNotFoundException;
import com.orange.orange_vote.constans.MemberErrorConstantsEnums;
import com.orange.orange_vote.constans.MemberRoleRelateErrorConstantsEnums;
import com.orange.orange_vote.constans.RoleErrorConstantsEnums;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.model.Role;
import com.orange.orange_vote.entity.service.MemberRoleRelateService;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.entity.service.RoleService;
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

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberRoleRelateService memberRoleRelateService;

    @Override
    protected void putAuthenticate(MemberCreateForm form, BindingResult errors) throws EntityNotFoundException {
        // if (!isValid(form.getPassword())) {
        // errors.rejectValue("password", MemberErrorConstantsEnums.MEMBER_PASSWORD_INVALID.valueOfCode(),
        // MemberErrorConstantsEnums.MEMBER_PASSWORD_INVALID.valueOfName());
        // }

        Member member = memberService.getMemberByOrangeId(form.getOrangeId()).orElse(null);
        if (member != null) {
            errors.rejectValue("orangeId", MemberErrorConstantsEnums.MEMBER_ORANGEID_DUPLICATE.valueOfCode(),
                MemberErrorConstantsEnums.MEMBER_ORANGEID_DUPLICATE.valueOfName());
        }
        List<Role> roleList = Lists.newArrayList();
        if(form.getRoleUuids() != null) {
            AtomicInteger index = new AtomicInteger();
            form.getRoleUuids().forEach(roleUuid -> {
                int i = index.getAndIncrement();
                Role role = roleService.getRoleByRoleUuid(roleUuid).orElse(null);
                if (role == null) {
                    errors.rejectValue("roleUuids[" + i + "]", RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfCode(),
                            RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfName());
                } else {
                    roleList.add(role);
                }
            });
        }
        form.setRoleList(roleList);
    }

    @Override
    protected void postAuthenticate(MemberUpdateForm form, BindingResult errors) throws EntityNotFoundException {
        List<Role> roleList = Lists.newArrayList();
        List<MemberRoleRelate> memberRoleRelateList = Lists.newArrayList();

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

        AtomicInteger index = new AtomicInteger();
        if (form.getRoleUuids() != null) {
            form.getRoleUuids().forEach(roleUuid -> {
                int i = index.getAndIncrement();
                Role role = roleService.getRoleByRoleUuid(roleUuid).orElse(null);
                MemberRoleRelate memberRoleRelate = memberRoleRelateService
                    .getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
                if (role == null) {
                    errors.rejectValue("roleUuids[" + i + "]", RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfCode(),
                        RoleErrorConstantsEnums.ROLE_NOTFOUND.valueOfName());
                } else if (memberRoleRelate != null) {
                    errors.rejectValue("roleUuids[" + i + "]",
                        MemberRoleRelateErrorConstantsEnums.MEMBER_ROLE_RELATE_DUPLICATE.valueOfCode(),
                        MemberRoleRelateErrorConstantsEnums.MEMBER_ROLE_RELATE_DUPLICATE.valueOfName());
                } else {
                    roleList.add(role);
                }

            });
        }

        if (form.getDeleteRoleUuids() != null) {
            index.set(0);
            form.getDeleteRoleUuids().forEach(roleUuid -> {
                int i = index.getAndIncrement();
                MemberRoleRelate memberRoleRelate = memberRoleRelateService
                    .getMemberRoleRelateByRoleUuidAndMemberId(roleUuid, member.getMemberId()).orElse(null);
                if (memberRoleRelate == null) {
                    errors.rejectValue("deleteRoleUuids[" + i + "]",
                        MemberRoleRelateErrorConstantsEnums.MEMBER_ROLE_RELATE_NOTFOUND.valueOfCode(),
                        MemberRoleRelateErrorConstantsEnums.MEMBER_ROLE_RELATE_NOTFOUND.valueOfName());
                } else {
                    memberRoleRelateList.add(memberRoleRelate);
                }
            });
        }

        form.setRoleList(roleList);
        form.setMemberRoleRelateList(memberRoleRelateList);

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

    private static boolean isValid(String pwd) {
        if (pwd == null || pwd.length() < 8) {
            return false;
        }
        boolean containEnglish = false;
        boolean containDigit = false;
        int i = 0;
        while (i < pwd.length()) {
            if (containDigit && containEnglish) {
                break;
            }
            if (Character.isUpperCase(pwd.charAt(i)) || Character.isLowerCase(pwd.charAt(i))) {
                containEnglish = true;
            }
            if (Character.isDigit(pwd.charAt(i))) {
                containDigit = true;
            }
            i++;
        }
        return containDigit & containEnglish;
    }
}
