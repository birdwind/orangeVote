package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.annotation.Required;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberRoleRelateService;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.validator.MemberFormValidator;
import com.orange.orange_vote.validator.MemberRoleRelateFormValidator;
import com.orange.orange_vote.view.member.MemberCreateForm;
import com.orange.orange_vote.view.member.MemberDeleteForm;
import com.orange.orange_vote.view.member.MemberResource;
import com.orange.orange_vote.view.member.MemberRoleRelateForm;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import com.orange.orange_vote.view.member.converter.MemberCreateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberResourcePacker;
import com.orange.orange_vote.view.member.converter.MemberRoleRelateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberUpdateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/api/member"}, produces = "application/json;charset=utf-8")
@Api(tags = "會員模組")
public class MemberApiController {

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

    @Autowired
    private MemberFormValidator memberFormValidator;

    // bind validator
    @InitBinder(value = {"role_relate"})
    public void bindMemberRoleRelate(WebDataBinder binder) {
        binder.addValidators(memberRoleRelateFormValidator);
    }

    // bind validator
    @InitBinder(value = {"member"})
    public void bindMember(WebDataBinder binder) {
        binder.addValidators(memberFormValidator);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "新增會員")
    public MemberResource createMember(@AuthForm @Valid MemberCreateForm memberCreateForm,
                                       @AuthForm @Valid MemberRoleRelateForm memberRoleRelateForm) {
        Member member = memberCreateFormConverter.convert(memberCreateForm);
        member = memberService.saveWithRoleRelate(member, memberRoleRelateFormConverter.convert(memberRoleRelateForm, member));
        return memberResourcePacker.pack(memberViewConverter.convert(member));
    }

    @PostMapping(value = "")
    @ApiOperation(value = "更新會員")
    public MemberResource updateMember(@AuthForm @Valid MemberUpdateForm memberUpdateForm,
        @AuthForm @Valid @RequestPart(value = "role_relate") MemberRoleRelateForm memberRoleRelateForm) {
        Member member = memberUpdateFormConverter.convert(memberUpdateForm);
        member = memberService.saveWithRoleRelate(member, memberRoleRelateFormConverter.convert(memberRoleRelateForm, member));
        return memberResourcePacker.pack(memberViewConverter.convert(member));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "刪除會員")
    public MemberResource deleteMember(@AuthForm @Valid MemberDeleteForm memberDeleteForm){
        memberService.delete(memberDeleteForm.getMembers());
        return memberResourcePacker.pack();
    }

}
