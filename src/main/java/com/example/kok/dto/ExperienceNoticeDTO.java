package com.example.kok.dto;

import com.example.kok.audit.Period;
import com.example.kok.enumeration.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class ExperienceNoticeDTO{
    private Long id;
    private String experienceNoticeTitle;
    private String experienceNoticeSubtitle;
    private String experienceNoticeIntroduceJob;
    private String experienceNoticeEtc;
    private LocalDate experienceStartDate;
    private LocalDate experienceEndDate;
    private Status experienceNoticeStatus;
    private Long companyId;
    private String createdDatetime;
    private String updatedDatetime;

    private String companyScaleName;
    private Long remainingDays;
}
