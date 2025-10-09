package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceApplicantCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;

public interface ConsoleExperienceDetailService {
//    공고 상세 내용
    ConsoleExperienceListDTO getDetail(Long experienceNoticeId);

//    지원 목록
    public ConsoleExperienceApplicantCriteriaDTO getApplicateList(Long companyId, int page, RequestStatus status);
}
