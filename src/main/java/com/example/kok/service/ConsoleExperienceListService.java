package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.enumeration.Status;

import java.util.Map;

public interface ConsoleExperienceListService {
//    공고 목록
    public ConsoleExperienceListCriteriaDTO getList(Long companyId, int page, Status status, String keyword);

//    공고 상태 변경
    public void updateListStatus(Long noticeId, Status status);

}
