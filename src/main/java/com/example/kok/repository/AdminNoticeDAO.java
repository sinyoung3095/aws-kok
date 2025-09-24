package com.example.kok.repository;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.mapper.AdminNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminNoticeDAO {
    private final AdminNoticeMapper adminNoticeMapper;

//    등록
    public void insert (AdminNoticeVO adminNoticeVO){
        adminNoticeMapper.insertNotice(adminNoticeVO);
    }

//    목록

//    수정

//    삭제
}
