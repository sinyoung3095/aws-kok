package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListRequestDTO;
import com.example.kok.enumeration.Status;

public interface ConsoleExperienceNoticeService {
//    공고 목록
    public ConsoleExperienceListCriteriaDTO getList(Long companyId, int page, Status status, String keyword);

//    공고 상태 변경
    public void updateListStatus(Long noticeId, Status status);

//    공고 상세
    ConsoleExperienceListRequestDTO getExperienceDetail(Long id);

//    공고 등록
    public void registerNotice(ConsoleExperienceListRequestDTO noticeRequestDTO);

//    공고 수정 등록
    public void modifyNotice(ConsoleExperienceListRequestDTO noticeRequestDTO);

//    공고 수정 상세
    ConsoleExperienceListRequestDTO getNotice(Long id);

//    공고 삭제
    public void deleteExperience(Long id);

//    체험 공고 마감 처리
    public void closeNotice();
}
