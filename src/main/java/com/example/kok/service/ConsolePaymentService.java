package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.dto.ConsolePaymentCriteriaDTO;
import com.example.kok.enumeration.Status;

public interface ConsolePaymentService {
//    결제 목록
    public ConsolePaymentCriteriaDTO getList(Long companyId, int page);

}
