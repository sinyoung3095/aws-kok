package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class CommentDTO{
    private Long id;
    private String commentContent;
    private Status commentStatus;
    private Long memberId;
    private Long postId;
    private String createdDateTime;
    private String updatedDateTime;
}
