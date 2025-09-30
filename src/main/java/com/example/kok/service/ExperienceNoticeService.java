package com.example.kok.service;

import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.util.Search;

import java.util.List;

public interface ExperienceNoticeService {

//    목록 조회
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page, Search search);

//    기업 프로필 사진 url
    public void setPreSignedUrl(CompanyProfileFileDTO companyProfileFileDTO);

//    공고 단일 조회
    public ExperienceNoticeDTO findNoticeById(Long id);

//    최신 체험 공고 4개 조회
    public List<ExperienceNoticeDTO> findLatestFour();
}
