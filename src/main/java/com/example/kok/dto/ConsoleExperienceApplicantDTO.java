package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleExperienceApplicantDTO {
    private long requestId; // 지원 요청 ID
    private String requestExperienceStatus; // 지원 상태
    private LocalDateTime requestDatetime; // 지원 일시

    private long userId; // 지원자 ID
    private String userName; // 지원자 이름
    private String userEmail; // 이메일
    private String userPhone; // 핸드폰

    private long experienceNoticeId; // 공고 ID
    private String experienceNoticeTitle; // 공고 제목
    private long companyId; // 기업 ID
}
