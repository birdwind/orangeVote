package com.orange.orange_vote.base.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class Authority {

    public final static SimpleGrantedAuthority SUPER_ADMIN = new SimpleGrantedAuthority("SUPER_ADMIN");

    public static SimpleGrantedAuthority getInstance(String roleKey) {
        return new SimpleGrantedAuthority(roleKey);
    }
}
