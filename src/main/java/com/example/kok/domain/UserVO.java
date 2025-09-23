package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@SuperBuilder
public class UserVO extends Period {
    private long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private Role userRole;
    private Status userStatus;
}
