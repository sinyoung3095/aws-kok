package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PostDTO {
    private long id;
    private String postContent;
    private Status postStatus;
    private long memberId;
    private String createdDateTime;
    private String updatedDateTime;
}
