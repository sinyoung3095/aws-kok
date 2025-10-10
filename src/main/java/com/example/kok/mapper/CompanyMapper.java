package com.example.kok.mapper;

import com.example.kok.domain.CompanyVO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.util.CompanySearch;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {
    //    회사 조회
    public CompanyDTO selectCompanyById(Long id);
    //    팔로워 수 조회
    public int selectFollowerCountByCompanyId(Long companyId);
    //    체험 공고 수 조회
    public int selectExperienceById(Long companyId);
    public int selectExperienceByEndDate(Long companyId);
    //    인턴 공고 수 조회
    public int selectInternById(Long companyId);
    public int selectInternByEndDate(Long companyId);
    //    회사 규모 조회
    public String selectScaleById(Long companyId);
    //    기업회원 회원 가입
    public void insertCompany(CompanyDTO  companyDTO);

//    기업 목록
    public List<CompanyDTO> selectCompanyAll(@Param("criteria") Criteria criteria, @Param("search") CompanySearch search, @Param("userId") Long userId);

//    기업 수
    public int selectCompanyCountByUserId(@Param("search") CompanySearch search);

//    인기 기업 목록
    public List<CompanyDTO> selectCompaniesByFollowerCount();
}
