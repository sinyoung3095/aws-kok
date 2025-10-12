package com.example.kok.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleInternApplicantDTO {
    private long requestId; // 지원 요청 ID
    private String requestInternStatus; // 지원 상태
    private LocalDateTime requestDatetime; // 지원 일시

    private long userId; // 지원자 ID
    private String userName; // 지원자 이름
    private String userEmail; // 이메일
    private String userPhone; // 핸드폰

    private long internNoticeId; // 공고 ID
    private String internNoticeTitle; // 공고 제목
    private long companyId; // 기업 ID
}
