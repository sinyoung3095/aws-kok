package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.repository.CompanyDAO;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.repository.InternNoticeDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;
    private final InternNoticeDAO internNoticeDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;


    @Override
public CompanyDTO findCompanyById(Long companyId) {
    CompanyDTO company = companyDAO.findCompanyById(companyId);
    if (company == null) {
        return null;
    }

    Integer followerCount = companyDAO.findFollowCount(companyId);
    company.setFollowerCount(followerCount);

    Integer experienceCount = companyDAO.findExperienceById(companyId);
    company.setExperienceCount(experienceCount);

    Integer internCount = companyDAO.findInternById(companyId);
    company.setInternCount(internCount);

    String scaleName = companyDAO.findScaleById(companyId);
    company.setScaleName(scaleName);

    return company;
}

    @Override
    public AdminCompanyCriteriaDTO findAllCompanies(int page, String keyword) {
        AdminCompanyCriteriaDTO adminCompanyCriteriaDTO = new AdminCompanyCriteriaDTO();
        Criteria criteria = new Criteria(page, companyDAO.selectCompanyCount(keyword));

        List<AdminCompanyDTO> companies = companyDAO.selectCompanyList(criteria, keyword);

        criteria.setHasMore(companies.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        criteria.setHasMore(companies.size() == criteria.getRowCount() + 1);
//        10개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            companies.remove(companies.size() - 1);
        }

        adminCompanyCriteriaDTO.setAdminCompanyDTOList(companies);
        adminCompanyCriteriaDTO.setCriteria(criteria);
        adminCompanyCriteriaDTO.setTotal(companyDAO.selectCompanyCount(keyword));

        return adminCompanyCriteriaDTO;
    }

//    아이디로 기업 조회
    @Override
    @Cacheable(value = "company", key="'company_' + #userId")
    public Optional<AdminCompanyDTO> findCompany(Long userId) {
        return companyDAO.selectCompany(userId)
                .map(adminCompanyDTO -> {
                    List<InternNoticeDTO> internNotices =
                            internNoticeDAO.findInternNotices(userId);
                    List<ExperienceNoticeDTO> experienceNotices =
                            experienceNoticeDAO.selectListById(userId);
                    adminCompanyDTO.setInternNoticeDTO(internNotices);
                    adminCompanyDTO.setExperienceNoticeDTO(experienceNotices);
                    return adminCompanyDTO;
                });
    }

}
