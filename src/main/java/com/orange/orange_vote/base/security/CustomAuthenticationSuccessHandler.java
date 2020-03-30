package com.orange.orange_vote.base.security;

import com.orange.orange_vote.base.enums.OperatorMethod;
import com.orange.orange_vote.base.enums.OperatorType;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.base.security.view.converter.LoginViewConverter;
import com.orange.orange_vote.base.system.converter.SystemResourcePacker;
import com.orange.orange_vote.base.utils.CipherUtils;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.base.utils.ServletUtils;
import com.orange.orange_vote.constans.ErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.service.FunctionOperatorRelateService;
import com.orange.orange_vote.entity.service.MemberService;
import com.orange.orange_vote.view.function.FunctionView;
import com.orange.orange_vote.view.module.ModuleView;
import com.orange.orange_vote.view.system.ModuleAuthView;
import com.orange.orange_vote.view.system.converter.ModuleAuthViewConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SystemResourcePacker systemResourcePacker;

    @Autowired
    private LoginViewConverter loginViewConverter;

    @Autowired
    private ModuleAuthViewConverter moduleAuthViewConverter;

    @Autowired
    private FunctionOperatorRelateService functionOperatorRelateService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        SystemUser systemUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberService.getMemberByUsername(systemUser.getUsername())
            .orElseThrow(() -> new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_NOT_FOUND)));

        String sessionString = CipherUtils.bcrypt(member.getMemberId().toString());

        ModuleAuthView moduleAuthView = moduleAuthViewConverter.convert(member);

        Set<Integer> functionIds = moduleAuthView.getModules().stream().map(ModuleView::getFunction)
            .flatMap(List::stream).mapToInt(FunctionView::getFunctionId).boxed().collect(Collectors.toSet());

        // 根據 function, operator, function_operator_relate 3張表格
        // 產生該帳號能夠存取的 URL
        // URL 格式如下: GET - /page/member/form, PUT - /api/member/, POST - /api/member/
        Set<String> urls =
            functionOperatorRelateService.getFunctionOperatorRelatesByFunctionIds(functionIds).parallelStream()
                .map(relate -> OperatorMethod.getName(relate.getOperator().getMethod()) + " - "
                    + OperatorType.getUrl(relate.getOperator().getType()) + relate.getFunction().getBackUrl()
                    + relate.getOperator().getUrl())
                .collect(Collectors.toSet());

        memberService.updateSession(sessionString, member.getMemberId());
        member.setSession(sessionString);
        systemUser.setSession(sessionString);
        systemUser.setCore(member);

        systemUser.setUrls(urls);

        // String res = systemUser.getUsername() + " Login Success";
        // systemLogService.setResponseAndLog(response, systemResourcePacker.packErrors(HttpStatus.OK, res));
        // ServletUtils.setResponse(response, systemResourcePacker.packErrors(HttpStatus.OK, res));
        ServletUtils.setResponse(response, systemResourcePacker.pack(loginViewConverter.convert(member)));
    }

}
