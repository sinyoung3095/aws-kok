package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class RequestInternFileDTO {
    private long id;
    private RequestStatus requestInternStatus;
    private long memberId;
    private long internNoticeId;
    private long memberAlarmSettingId;
    private long evaluationId;
    private String createdDateTime;
    private String updatedDateTime;
}
