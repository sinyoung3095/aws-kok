package com.example.kok.mapper;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.AdminNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminNoticeMapper {
//    공지사항 등록
    public void insertNotice (AdminNoticeVO adminNoticeVO);

//    공지사항 목록
    public List<AdminNoticeDTO> selectnoticeAll (AdminNoticeDTO adminNoticeDTO);

//    공지사항 수정
    public void updateNoticeFromId(AdminNoticeDTO adminNoticeDTO);

//    공지사항 삭제
    public void deleteNoticeFromId(Long id);
}
