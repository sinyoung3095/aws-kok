package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class InternNoticeDTO {
    private Long id;
    private Long companyId;
    private String internNoticeTitle;
    private String internNoticeSubTitle;
    private String internNoticeIntroduceJob;
    private String internNoticeMajorWork;
    private String internNoticeNotes;
    private Status internNoticeStatus;
    private String createdDateTime;
    private String updatedDateTime;
}
