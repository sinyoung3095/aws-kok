package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.AdminExperienceCriteriaDTO;
import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.util.Search;

import java.util.Optional;

public interface AdminService {
//    체험 목록
    public AdminExperienceCriteriaDTO getExperienceNotice(int page, Search search);

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
