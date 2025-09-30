package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.util.Search;

import java.util.List;
import java.util.Optional;

public interface AdminNoticeService {
//    체험 목록

//    공지 등록
    public void write (AdminNoticeDTO adminNoticeDTO);

//    공지 상세
    public Optional<AdminNoticeDTO> getNotice (Long id);

//    공지 목록
    public AdminNoticeCriteriaDTO getList (int page);

//    공지 수정
    public void update (AdminNoticeDTO adminNoticeDTO);

//    공지 삭제
    public void delete (Long id);

    default AdminNoticeVO toVO(AdminNoticeDTO adminNoticeDTO){
        return AdminNoticeVO.builder()
                .id(adminNoticeDTO.getId())
                .adminNoticeTitle(adminNoticeDTO.getAdminNoticeTitle())
                .adminNoticeContent(adminNoticeDTO.getAdminNoticeContent())
                .noticeStatus(adminNoticeDTO.getNoticeStatus())
                .createdDateTime(adminNoticeDTO.getCreatedDateTime())
                .updatedDateTime(adminNoticeDTO.getUpdatedDateTime())
                .build();
    }
}
