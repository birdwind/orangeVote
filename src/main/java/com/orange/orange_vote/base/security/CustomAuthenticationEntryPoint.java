package com.orange.orange_vote.base.security;

import com.orange.orange_vote.base.constans.BaseErrorConstants;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        AuthenticationException authenticationException) throws ServletException {
        try {
            // System.out.println("Custom" + "false");
            ServletUtils.setResponse(httpServletResponse, systemResourcePacker.packErrors(HttpStatus.UNAUTHORIZED,
                LocaleI18nUtils.getString(BaseErrorConstants.NO_PERMISSION)));
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
