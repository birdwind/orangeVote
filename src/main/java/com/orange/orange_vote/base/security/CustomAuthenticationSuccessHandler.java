package com.orange.orange_vote.base.security;

import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.entity.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SideMenuViewConverter sideMenuViewConverter;

    @Autowired
    private SystemResourcePacker systemResourcePacker;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        SystemUser systemUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberService.getMemberCoreByUsername(systemUser.getUsername()).orElseThrow(
            () -> new BadCredentialsException(LocaleI18nUtils.getString(BaseErrorConstants.MEMBER_NOT_FOUND)));

        SideMenuView sideMenuView = sideMenuViewConverter.convert(memberCore);
        String sessionString = CipherUtils.bcrypt(memberCore.getMemberCoreId().toString());

        Set<Integer> functionIds = sideMenuView.getModules().stream().map(ModuleView::getFunction).flatMap(List::stream)
            .mapToInt(FunctionView::getFunctionId).boxed().collect(Collectors.toSet());

        // 根據 function, operator, function_operator_relate 3張表格
        // 產生該帳號能夠存取的 URL
        // URL 格式如下: GET - /page/member/form, PUT - /api/member/, POST - /api/member/
        Set<String> urls = functionOperatorRelateService.getFunctionOperatorRelatesByFunctionIds(functionIds).parallelStream()
            .map(relate -> OperatorMethod.getName(relate.getOperator().getMethod()) + " - "
                + OperatorType.getUrl(relate.getOperator().getType()) + relate.getFunction().getBackUrl()
                + relate.getOperator().getUrl())
            .collect(Collectors.toSet());

        // urls.addAll(sideMenuView.getModules().stream().map(ModuleView::getFunction).flatMap(List::stream)
        // .map(FunctionView::getBackUrl).collect(Collectors.toSet()));

        memberService.updateSession(sessionString, memberCore.getMemberCoreId());
        memberCore.setSession(sessionString);
        systemUser.setSession(sessionString);
        systemUser.setCore(memberCore);
        systemUser.setSideMenu(sideMenuView);
        systemUser.setUrls(urls);

        String res = systemUser.getUsername() + " 登入成功";
        systemLogService.setResponseAndLog(response, systemResourcePacker.packErrors(HttpStatus.OK, res));
    }

}
