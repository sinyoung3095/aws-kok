package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleAdNoticeDTO {
    private long id; // 결제 id
    private String advertisementMainText; // 광고 메인 텍스트
    private String advertisementSubText; // 광고 서브 텍스트
    private RequestStatus advertisementStatus; // 요청 상태
    private RequestStatus advertisementRequestStatus; // 승인 상태
    private LocalDate advertiseStartDatetime; // 광고 시작 날짜
    private LocalDate advertiseEndDatetime; // 광고 종료 날짜
    private long companyId; // 회사 아이디
    private String createdDateTime;
    private String updatedDateTime;
    private int paymentPrice; // 금액

    private List<PostFileDTO> postFiles;
}
