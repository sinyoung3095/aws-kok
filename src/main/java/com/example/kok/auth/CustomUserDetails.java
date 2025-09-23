package com.example.kok.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Spring Security에서는 기본으로 최소 정보만 제공한다.
// 추가 정보를 토큰에 담고 싶다면 CustomUserDetails를 구성하여 활용한다.
@Getter
public class CustomUserDetails implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
