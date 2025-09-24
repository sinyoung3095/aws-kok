package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ConsoleExperienceNoticeDTO {
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private LocalDate experienceStartDate;
    private LocalDate experienceEndDate;
    private String experienceNoticeStatus;
    private Long companyId;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;

    private String jobCategoryName;
    private int applicantCount;
    private int saveCount;
}
