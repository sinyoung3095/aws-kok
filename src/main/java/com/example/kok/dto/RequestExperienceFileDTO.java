package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="fileId")
public class RequestExperienceFileDTO {
    private long fileId;
    private long requestExperienceId;
}
