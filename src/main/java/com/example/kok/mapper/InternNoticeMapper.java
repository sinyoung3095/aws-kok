package com.example.kok.mapper;

import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.util.CompanyNoticeCriteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InternNoticeMapper {
//    기업별 인턴 공고 목록
    public List<InternNoticeDTO> selectInternNoticeByCompanyId(@Param("companyId") Long companyId, @Param("criteria") CompanyNoticeCriteria criteria, @Param("search") Search search);

//    기업별 인턴 공고 개수
    public int selectInternNoticeCountByCompanyId(@Param("companyId") Long companyId, @Param("search") Search search);
}
