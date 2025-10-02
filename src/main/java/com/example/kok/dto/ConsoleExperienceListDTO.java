package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleExperienceListDTO {
    private long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private LocalDate experienceStartDate;
    private LocalDate experienceEndDate;
    private LocalDate experienceNoticeStartDate;
    private LocalDate experienceNoticeEndDate;
    private Status experienceNoticeStatus;
    private long companyId;
    private String createdDateTime;
    private String updatedDateTime;

    private String jobCategoryName;
    private int applicantCount; //지원자수
    private int saveCount; //저장한 사람수
}
