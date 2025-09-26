package com.example.kok.auth;

import com.example.kok.dto.UserDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Spring Security에서는 기본으로 최소 정보만 제공한다.
// 추가 정보를 토큰에 담고 싶다면 CustomUserDetails를 구성하여 활용한다.
@Getter
@ToString
public class CustomUserDetails implements UserDetails {
    private Long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private UserRole userRole;
    private Status userStatus;

    public CustomUserDetails(UserDTO userDTO){
        this.id=userDTO.getId();
        this.userName=userDTO.getUserName();
        this.userPassword=userDTO.getUserPassword();
        this.userEmail=userDTO.getUserEmail();
        this.userPhone=userDTO.getUserPhone();
        this.userRole=userDTO.getUserRole();
        this.userStatus=userDTO.getUserStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }
}
