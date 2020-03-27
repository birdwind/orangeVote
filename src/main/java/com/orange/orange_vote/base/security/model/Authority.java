package com.orange.orange_vote.base.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class Authority {

    public final static SimpleGrantedAuthority ADMIN = new SimpleGrantedAuthority("ADMIN");

    public static SimpleGrantedAuthority getInstance(String roleKey) {
        return new SimpleGrantedAuthority(roleKey);
    }
}
