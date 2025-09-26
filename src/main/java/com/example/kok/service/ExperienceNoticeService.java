package com.example.kok.service;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.util.Search;

public interface ExperienceNoticeService {

//    목록 조회
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page, Search search);
}
