/*
 * Copyright (c) 2019. Create by Terry Huang (黃昭維)
 */

package com.orange.orange_vote.base.security;

import com.birdwind.rbac.base.constants.BaseErrorConstants;
import com.birdwind.rbac.base.security.Authority;
import com.birdwind.rbac.base.security.SystemUser;
import com.birdwind.rbac.base.util.LocaleI18nUtils;
import com.birdwind.rbac.repo.model.MemberCore;
import com.birdwind.rbac.repo.model.Role;
import com.birdwind.rbac.service.MemberService;
import com.birdwind.rbac.service.RoleService;
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

        MemberCore memberCore = memberService.getMemberCoreByUsername(username).orElseThrow(
            () -> new BadCredentialsException(LocaleI18nUtils.getString(BaseErrorConstants.MEMBER_NOT_FOUND)));

        if (!memberCore.getStatus())
            throw new BadCredentialsException(LocaleI18nUtils.getString(BaseErrorConstants.MEMBER_SUSPENDED));

        // 取得帳號擁有的所有角色
        List<Role> roles = roleService.getRolesByMemberCoreId(memberCore.getMemberCoreId());
        // 根據帳號有的 role 賦予 Spring Security Role
        Set<SimpleGrantedAuthority> authorities =
            roles.stream().map(Role::getRoleKey).map(Authority::getInstance).collect(Collectors.toSet());

        return getUserDetails(memberCore.getUsername(), memberCore.getPassword(), authorities);
    }

    private SystemUser getUserDetails(String account, String password,
        Collection<? extends GrantedAuthority> authorities) {
        return new SystemUser(account, password, true, true, true, true, authorities);
    }

}
