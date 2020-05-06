package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.repo.BaseModel;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.MemberRoleRelate;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.entity.service.RoleService;
import com.orange.orange_vote.validator.MemberFormValidator;
import com.orange.orange_vote.view.member.MemberCreateForm;
import com.orange.orange_vote.view.member.MemberDeleteForm;
import com.orange.orange_vote.view.member.MemberResource;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import com.orange.orange_vote.view.member.converter.MemberCreateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberResourcePacker;
import com.orange.orange_vote.view.member.converter.MemberUpdateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberViewConverter;
import com.orange.orange_vote.view.role.RoleResource;
import com.orange.orange_vote.view.role.converter.RoleListItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
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
    private MemberViewConverter memberViewConverter;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleListItemConverter roleListItemConverter;

    @Autowired
    private RoleService roleService;

    @PutMapping(value = "")
    @ApiOperation(value = "新增會員")
    public MemberResource createMember(@AuthForm @Valid MemberCreateForm memberCreateForm) {
        final Member member = memberCreateFormConverter.convert(memberCreateForm);
        List<MemberRoleRelate> memberRoleRelateList = memberCreateForm.getRoleList().stream()
            .map(role -> new MemberRoleRelate(member, role)).collect(Collectors.toList());
        Member memberTemp = memberService.saveWithRoleRelate(member, memberRoleRelateList);
        return memberResourcePacker.pack(memberViewConverter.convert(memberTemp));
    }

    @PostMapping(value = "")
    @ApiOperation(value = "更新會員")
    public MemberResource updateMember(@AuthForm @Valid MemberUpdateForm memberUpdateForm) {
        final Member member = memberUpdateFormConverter.convert(memberUpdateForm);
        List<MemberRoleRelate> memberRoleRelateList = memberUpdateForm.getRoleList().stream()
            .map(role -> new MemberRoleRelate(member, role)).collect(Collectors.toList());
        memberRoleRelateList.addAll(
            memberUpdateForm.getMemberRoleRelateList().stream().peek(BaseModel::delete).collect(Collectors.toList()));
        Member memberTemp = memberService.saveWithRoleRelate(member, memberRoleRelateList);
        return memberResourcePacker.pack(memberViewConverter.convert(memberTemp));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "刪除會員")
    public MemberResource deleteMember(@AuthForm @Valid MemberDeleteForm memberDeleteForm) {
        memberService.delete(memberDeleteForm.getMembers());
        return memberResourcePacker.pack();
    }

    @ApiOperation(value = "獲取角色清單")
    @GetMapping(value = "/role/list")
    public MemberResource getRole() {
        return memberResourcePacker.pack(roleListItemConverter.convert(roleService.getAllRoles()));
    }

}
