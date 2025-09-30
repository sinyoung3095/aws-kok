package com.example.kok.service;

import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.SaveExperienceNoticeDTO;
import com.example.kok.util.Search;

public interface ExperienceNoticeService {

//    목록 조회
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page, Search search);

//    기업 프로필 사진 url
    public void setPreSignedUrl(CompanyProfileFileDTO companyProfileFileDTO);

//    공고 단일 조회
    public ExperienceNoticeDTO findNoticeById(Long id);

//    공고 저장
    public void saveExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);

//    공고 저장 취소
    public void deleteExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);
}
