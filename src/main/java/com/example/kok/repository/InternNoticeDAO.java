package com.example.kok.repository;

import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.mapper.InternNoticeMapper;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InternNoticeDAO {
    private final InternNoticeMapper internNoticeMapper;

    //    기업별 인턴 공고 목록
    public List<InternNoticeDTO> findAllByCompanyId(Long companyId, CompanyNoticeCriteria criteria, Search search) {
        return internNoticeMapper.selectInternNoticeByCompanyId(companyId, criteria, search);
    }

    //    기업별 인턴 공고 갯수
    public int findCountByCompanyId(Long companyId, Search search) {
        return internNoticeMapper.selectInternNoticeCountByCompanyId(companyId, search);
    }

    //    기업회원 별 인턴 공고
    public List<InternNoticeDTO> findInternNotices(Long userId) {
        return internNoticeMapper.selectListById(userId);

    }

}


