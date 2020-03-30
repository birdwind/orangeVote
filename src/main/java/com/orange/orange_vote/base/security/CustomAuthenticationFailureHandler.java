package com.orange.orange_vote.base.security;

import com.orange.orange_vote.base.enums.ErrorCode;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.utils.ServletUtils;
import com.orange.orange_vote.constans.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    // @Autowired
    // private SystemLogService systemLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) {
        try {
            ServletUtils.setResponse(response,
                systemResourcePacker.packErrors(HttpStatus.UNAUTHORIZED,
                    LocaleI18nUtils.getString(ErrorConstants.MEMBER_NOT_FOUND), ErrorCode.LOGIN_ERROR.errorCode(),
                    ErrorCode.LOGIN_ERROR.errorMsg()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        // systemLogService.setResponseAndLog(response,
        // systemResourcePacker.packErrors(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

}
