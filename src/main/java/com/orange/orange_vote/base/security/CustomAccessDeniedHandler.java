/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.orange.orange_vote.base.security;

import com.birdwind.rbac.base.constants.BaseErrorConstants;
import com.birdwind.rbac.base.util.LocaleI18nUtils;
import com.birdwind.rbac.service.SystemLogService;
import com.birdwind.rbac.web.view.system.converter.SystemResourcePacker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws ServletException {
        try {
//            System.out.println("Custom" + "false");
            systemLogService.setResponseAndLog(response, systemResourcePacker.packErrors(HttpStatus.UNAUTHORIZED,
                LocaleI18nUtils.getString(BaseErrorConstants.NO_PERMISSION)));
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}
