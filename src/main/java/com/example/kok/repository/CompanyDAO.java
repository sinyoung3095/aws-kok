package com.example.kok.repository;

import com.example.kok.domain.CompanyVO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.mapper.CompanyMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyDAO {
    private final CompanyMapper companyMapper;

//    회사 조회
    public CompanyDTO findCompanyById(Long companyId) {
        return companyMapper.selectCompanyById(companyId);
    }

//    팔로워 수 조회
    public int findFollowCount(Long companyId){
        return companyMapper.selectFollowerCountByCompanyId(companyId);
    }

//    체험 공고 수 조회
    public int findExperienceById(Long companyId){
        return companyMapper.selectExperienceById(companyId);
    }

//    인턴 공고 수 조회
    public int findInternById(Long companyId){
        return companyMapper.selectInternById(companyId);
    }

//    회사 규모 조회
    public String findScaleById(Long companyId){
        return companyMapper.selectScaleById(companyId);
    }
    //    기업회원 회원 가입
    public void saveCompany(CompanyDTO  companyDTO){
        companyMapper.insertCompany(companyDTO);
    };

//    기업 목록
    public List<CompanyDTO> findCompanies(Criteria criteria) {
        return companyMapper.selectCompanyAll(criteria);
    }

//    기업 수
    public int findTotalCount() {
        return companyMapper.selectCompanyCount();
    }
}
