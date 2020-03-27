package com.orange.orange_vote.base.security;

import org.springframework.stereotype.Service;

@Service(value = "customUserDetailsServiceImpl")
public class CustomUserDetailsServiceImpl
// implements UserDetailsService
{

    // @Autowired
    // private MemberService memberService;
    //
    // @Autowired
    // private RoleService roleService;
    //
    // @Transactional(readOnly = true)
    // public UserDetails loadUserByUsername(String username) {
    // if (StringUtils.isBlank(username)) {
    // throw new BadCredentialsException(
    // LocaleI18nUtils.getString(LocaleI18nUtils.getString(BaseErrorConstants.NULL)));
    // }
    //
    // Member memberCore = memberService.getMemberByUsername(username).orElseThrow(
    // () -> new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_NOT_FOUND)));
    //
    // if (!memberCore.getStatus())
    // throw new BadCredentialsException(LocaleI18nUtils.getString(ErrorConstants.MEMBER_SUSPENDED));
    //
    // // 取得帳號擁有的所有角色
    // List<Role> roles = roleService.getRolesByMemberCoreId(memberCore.getMemberCoreId());
    // // 根據帳號有的 role 賦予 Spring Security Role
    // Set<SimpleGrantedAuthority> authorities =
    // roles.stream().map(Role::getRoleKey).map(Authority::getInstance).collect(Collectors.toSet());
    //
    // return getUserDetails(memberCore.getUsername(), memberCore.getPassword(), authorities);
    // }
    //
    // private SystemUser getUserDetails(String account, String password,
    // Collection<? extends GrantedAuthority> authorities) {
    // return new SystemUser(account, password, true, true, true, true, authorities);
    // return new UserDetails();
    // }

}
