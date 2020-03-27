/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.orange.orange_vote.base.security;

import com.birdwind.rbac.service.SystemLogService;
import com.birdwind.rbac.web.view.system.converter.SystemResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        systemLogService.setResponseAndLog(response,
            systemResourcePacker.packErrors(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

}
