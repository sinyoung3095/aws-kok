package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ExperienceNoticeDTO {
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private LocalDate ExperienceStartDate;
    private LocalDate ExperienceEndDate;
    private String experienceNoticeNotes;
    private Status experienceNoticeStatus;
    private Long companyId;
    private String createdDateTime;
    private String updatedDateTime;
}
