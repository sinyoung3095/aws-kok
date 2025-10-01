package com.example.kok.mapper;

import com.example.kok.domain.CompanyVO;
import com.example.kok.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    //    회사 조회
    public CompanyDTO selectCompanyById(Long id);
    //    팔로워 수 조회
    public int selectFollowerCountByCompanyId(Long companyId);
    //    체험 공고 수 조회
    public int selectExperienceById(Long companyId);
    //    인턴 공고 수 조회
    public int selectInternById(Long companyId);
    //    회사 규모 조회
    public String selectScaleById(Long companyId);
    //    기업회원 회원 가입
    public void insertCompany(CompanyDTO  companyDTO);
}
