package com.example.kok.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
public class ConsoleExperienceNoticeCriteriaDTO {
    private List<ConsoleExperienceNoticeDTO> experienceNotices;

    private String keyword;
//    private com.example.kok.util.Criteria criteria;
}
