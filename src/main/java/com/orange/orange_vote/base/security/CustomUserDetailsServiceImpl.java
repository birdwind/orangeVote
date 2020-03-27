package com.orange.orange_vote.base.security;

import com.orange.orange_vote.MemberService;
import com.orange.orange_vote.RoleService;
import com.orange.orange_vote.base.constans.BaseErrorConstants;
import com.orange.orange_vote.base.security.model.Authority;
import com.orange.orange_vote.base.security.model.SystemUser;
import com.orange.orange_vote.base.utils.LocaleI18nUtils;
import com.orange.orange_vote.constans.ErrorConstants;
import com.orange.orange_vote.entity.model.Member;
import com.orange.orange_vote.entity.model.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "customUserDetailsServiceImpl")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException(
                LocaleI18nUtils.getString(LocaleI18nUtils.getString(BaseErrorConstants.NULL)));
        }

        Member member = memberService.getMemberByUsername(username)
            .orElseThrow(() -> new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_NOT_FOUND)));

        if (!member.getStatus())
            throw new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_SUSPENDED));

        // 取得帳號擁有的所有角色
        List<Role> roles = roleService.getRolesByMemberId(member.getMemberId());
        // 根據帳號有的 role 賦予 Spring Security Role
        Set<SimpleGrantedAuthority> authorities =
            roles.stream().map(Role::getRoleKey).map(Authority::getInstance).collect(Collectors.toSet());

        return getUserDetails(member.getUsername(), member.getPassword(), authorities);
    }

    private SystemUser getUserDetails(String account, String password,
        Collection<? extends GrantedAuthority> authorities) {
        return new SystemUser(account, password, true, true, true, true, authorities);
//        return new UserDetails();
    }

}
