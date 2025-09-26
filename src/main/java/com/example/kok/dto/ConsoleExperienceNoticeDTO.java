package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleExperienceNoticeDTO {
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

    private String jobCategoryName;
    private int applicantCount;
    private int saveCount;
}
