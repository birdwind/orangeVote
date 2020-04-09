package com.orange.orange_vote.controller;

import com.orange.orange_vote.base.annotation.AuthForm;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.validator.MemberFormValidator;
import com.orange.orange_vote.view.member.MemberUpdateForm;
import com.orange.orange_vote.view.member.converter.MemberUpdateFormConverter;
import com.orange.orange_vote.view.member.converter.MemberViewConverter;
import com.orange.orange_vote.view.personal.converter.PersonalResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(value = {"/api/personal"})
public class PersonalController {

    @Autowired
    private PersonalResourcePacker personalResourcePacker;

    @Autowired
    private MemberUpdateFormConverter memberUpdateFormConverter;

    @Autowired
    private MemberViewConverter memberViewConverter;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberFormValidator memberFormValidator;

    // bind validator
    @InitBinder(value = {"member"})
    public void bindMember(WebDataBinder binder) {
        binder.addValidators(memberFormValidator);
    }

    //TODO:還沒測試
    @PostMapping(value = "")
    public String updatePersonal(@AuthForm @Valid @RequestPart(value = "member") MemberUpdateForm memberUpdateForm) {
        Member member = memberUpdateFormConverter.convert(memberUpdateForm, SystemUser.getMember());
        return personalResourcePacker.pack(memberViewConverter.convert(memberService.save(member))).toJson();
    }
}
