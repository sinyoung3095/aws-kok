package com.example.kok.service;

import com.example.kok.dto.CompaniesCriteriaDTO;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.ReplyDTO;
import com.example.kok.repository.CompanyDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;
    private final S3Service s3Service;


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

//    기업 목록
    @Override
    public CompaniesCriteriaDTO getCompanyList(int page) {
        Criteria criteria = new Criteria(page, companyDAO.findTotalCount());
        List<CompanyDTO> companies = companyDAO.findCompanies(criteria);

        companies.forEach(company -> {
            company.setCompanyProfileFile(
                s3Service.getPreSignedUrl(company.getCompanyProfileFile(), Duration.ofMinutes(10))
            );

            company.setCompanyBackgroundFile(
                s3Service.getPreSignedUrl(company.getCompanyBackgroundFile(), Duration.ofMinutes(10))
            );
        });

        criteria.setHasMore(criteria.getPage() < criteria.getRealEnd());
        if (criteria.isHasMore() && !companies.isEmpty()) {
            companies.remove(companies.size() - 1);
        }

        CompaniesCriteriaDTO companiesCriteriaDTO = new CompaniesCriteriaDTO();
        companiesCriteriaDTO.setCompanies(companies);
        companiesCriteriaDTO.setCriteria(criteria);

        return companiesCriteriaDTO;
    }

}
