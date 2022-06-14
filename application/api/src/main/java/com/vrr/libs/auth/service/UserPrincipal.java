package com.vrr.libs.auth.service;

import com.vrr.code.auth.ProviderType;
import com.vrr.code.auth.RoleType;
import com.vrr.entity.auth.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Builder
public class UserPrincipal implements UserDetails {

    private final String id;
    private final String username;
    private final String email;
    private final String password;
    private final ProviderType providerType;
    private final RoleType roleType;
    private final String emailVerified;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public static UserPrincipal build(final User user) {
        return UserPrincipal.builder()
                .id(user.getUuid())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .emailVerified(user.getEmailVerified())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(RoleType.USER.getCode())))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
