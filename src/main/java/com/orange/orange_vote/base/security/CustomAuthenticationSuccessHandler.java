package com.orange.orange_vote.base.security;

import com.orange.orange_vote.MemberService;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import com.orange.orange_vote.base.utils.CipherUtils;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.utils.ServletUtils;
import com.orange.orange_vote.constans.ErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        SystemUser systemUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberService.getMemberByUsername(systemUser.getUsername())
            .orElseThrow(() -> new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_NOT_FOUND)));

        String sessionString = CipherUtils.bcrypt(member.getMemberId().toString());

        memberService.updateSession(sessionString, member.getMemberId());
        member.setSession(sessionString);
        systemUser.setSession(sessionString);
        systemUser.setCore(member);

        String res = systemUser.getUsername() + " 登入成功";
        // systemLogService.setResponseAndLog(response, systemResourcePacker.packErrors(HttpStatus.OK, res));
        ServletUtils.setResponse(response, systemResourcePacker.packErrors(HttpStatus.OK, res));
    }

}
