package com.example.kok.service;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.repository.CompanyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;


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

}
