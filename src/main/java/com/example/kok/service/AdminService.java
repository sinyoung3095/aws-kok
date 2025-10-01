package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.*;
import com.example.kok.util.Search;

import java.util.Optional;

public interface AdminService {

//    체험
    public AdminExperienceDetailDTO getExperience(int page, Search search, Long id);

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
