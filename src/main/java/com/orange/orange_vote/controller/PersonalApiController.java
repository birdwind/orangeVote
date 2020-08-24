package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.member.converter.MemberViewConverter;
import com.orange.orange_vote.view.personal.PersonalResource;
import com.orange.orange_vote.view.personal.PersonalUpdateForm;
import com.orange.orange_vote.view.personal.converter.PersonalOrangePaperworkViewConverter;
import com.orange.orange_vote.view.personal.converter.PersonalResourcePacker;
import com.orange.orange_vote.view.personal.converter.PersonalUpdateFormConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = {"/api/personal"}, produces = "application/json;charset=utf-8")
@Api(tags = "個人資料模組")
public class PersonalApiController {

    @Autowired
    private PersonalResourcePacker personalResourcePacker;

    @Autowired
    private PersonalUpdateFormConverter personalUpdateFormConverter;

    @Autowired
    private MemberViewConverter memberViewConverter;

    @Autowired
    private PersonalOrangePaperworkViewConverter personalOrangePaperworkViewConverter;

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "更新個人資料")
    @PostMapping(value = "")
    public PersonalResource updatePersonal(@AuthForm @Valid PersonalUpdateForm personalUpdateForm) {

        Member memberLocal = personalUpdateFormConverter.convert(personalUpdateForm);
        Member member = memberService.save(memberLocal);

        return personalResourcePacker.pack(memberViewConverter.convert(member));
    }

    @ApiOperation(value = "獲得個人資料")
    @GetMapping(value = "")
    public PersonalResource getPersonalInfo() {
        Member localMember = SystemUser.getMember();
        Member member = memberService.getMemberByMemberUuid(localMember.getMemberUuid()).orElse(null);
        return personalResourcePacker.pack(memberViewConverter.convert(member));
    }

    @ApiOperation(value = "獲得椪柑證")
    @GetMapping(value = "/orange")
    public PersonalResource getOrangePaperwork() {
        Member localMember = SystemUser.getMember();
        Member member = memberService.getMemberByMemberUuid(localMember.getMemberUuid()).orElse(null);
        return personalResourcePacker.pack(personalOrangePaperworkViewConverter.convert(member));
    }

}
