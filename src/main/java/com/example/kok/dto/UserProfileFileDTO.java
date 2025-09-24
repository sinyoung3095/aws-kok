package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import com.example.kok.enumeration.UserRole;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="fileId")
public class UserProfileFileDTO {
    private long fileId;
    private long userId;
}
