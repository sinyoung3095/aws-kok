package com.example.kok.repository;

import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.mapper.InternNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InternNoticeDAO {
    private final InternNoticeMapper internNoticeMapper;

//    기업회원 별 인턴 공고
    public List<InternNoticeDTO> findInternNotices(Long userId) {
        return internNoticeMapper.selectListById(userId);
    }
}
