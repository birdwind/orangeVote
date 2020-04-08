package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.validator.MemberRoleRelateFormValidator;
import com.orange.orange_vote.view.member.MemberCreateForm;
import com.orange.orange_vote.view.member.MemberRoleRelateForm;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import com.orange.orange_vote.view.member.converter.MemberCreateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberResourcePacker;
import com.orange.orange_vote.view.member.converter.MemberRoleRelateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberUpdateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/member"})
public class MemberController {

    @Autowired
    private MemberResourcePacker memberResourcePacker;

    @Autowired
    private MemberCreateFormConverter memberCreateFormConverter;

    @Autowired
    private MemberUpdateFormConverter memberUpdateFormConverter;

    @Autowired
    private MemberRoleRelateFormConverter memberRoleRelateFormConverter;

    @Autowired
    private MemberViewConverter memberViewConverter;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRoleRelateFormValidator memberRoleRelateFormValidator;

    // bind validator
    @InitBinder(value = {"role_relate"})
    public void bindOrderSchedule(WebDataBinder binder) {
        binder.addValidators(memberRoleRelateFormValidator);
    }

    @PutMapping(value = "")
    public String createMember(@AuthForm @Valid @RequestPart(value = "member") MemberCreateForm memberCreateForm,
        @AuthForm @Valid @RequestPart(value = "role_relate") MemberRoleRelateForm memberRoleRelateForm) {
        Member member = memberCreateFormConverter.convert(memberCreateForm);
        member = memberService.save(member, memberRoleRelateFormConverter.convert(memberRoleRelateForm, member));
        return memberResourcePacker.pack(memberViewConverter.convert(member)).toJson();
    }

    @PostMapping(value = "")
    public String updateMember(@AuthForm @Valid @RequestPart(value = "member") MemberUpdateForm memberUpdateForm,
        @AuthForm @Valid @RequestPart(value = "role_relate") MemberRoleRelateForm memberRoleRelateForm) {
        Member member = memberUpdateFormConverter.convert(memberUpdateForm);
        member = memberService.save(member, memberRoleRelateFormConverter.convert(memberRoleRelateForm, member));
        return memberResourcePacker.pack(memberViewConverter.convert(member)).toJson();
    }

}
