package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class UserDTO {
    private long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private Role userRole;
    private Status userStatus;
    private String createdDateTime;
    private String updatedDateTime;
}
