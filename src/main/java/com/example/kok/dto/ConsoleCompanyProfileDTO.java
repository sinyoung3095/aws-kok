package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="userId")
@NoArgsConstructor
public class ConsoleCompanyProfileDTO {
    private long userId;
    private String companyName; // 기업명
    private String companyInfo; // 기업 소개
    private String companyUrl; // 홈페이지 URL
    private String ceoName;
    private String createdDatetime;
    private String updatedDatetime;

    private String companySectorName; // 산업 분야
    private String companyScaleName; // 기업 규모

    private String companyBackgroundFile;
    private String companyProfileFile;
}
