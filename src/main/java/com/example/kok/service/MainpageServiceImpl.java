package com.example.kok.service;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainpageServiceImpl implements MainpageService {
    private final CompanyDAO companyDAO;
    private final S3Service s3Service;
    private final InternNoticeDAO internNoticeDAO;
    private final ExperienceNoticeDAO experienceNoticeDAO;
    private final FollowDAO followDAO;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final UserProfileService userProfileService;
    @Override
    public List<CompanyDTO> findPopularCompanies() {
        List<CompanyDTO> companyDTOs = followDAO.selectPopularCompany();
        companyDTOs.forEach(companyDTO -> {

            if(companyProfileFileDAO.findFileByCompanyId(companyDTO.getUserId())==null) {

            }else{
                companyDTO.setCompanyProfileFile(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(companyDTO.getUserId()).getFilePath(), Duration.ofMinutes(10)));
            }
            companyDTO.setFollowerCount(followDAO.countFollowersByCompanyId(companyDTO.getUserId()));
        });
        return companyDTOs;
    }

    @Override
    public List<ExperienceNoticeDTO> findExperienceNotices(String keyword) {
        List<ExperienceNoticeDTO> experienceNoticeDTOS = experienceNoticeDAO.findAllByKeyword(keyword);
        experienceNoticeDTOS.forEach(experienceNoticeDTO -> {
            if(companyProfileFileDAO.findFileByCompanyId(experienceNoticeDTO.getCompanyId())==null){}
            else{
                experienceNoticeDTO.setFilePath(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(experienceNoticeDTO.getCompanyId()).getFilePath(), Duration.ofMinutes(10)));
            }
        });
        return experienceNoticeDTOS;
    }

    @Override
    public List<InternNoticeDTO> findInternNotices(String keyword) {
        List<InternNoticeDTO>  internNoticeDTOS = internNoticeDAO.findAllByKeyword(keyword);
        internNoticeDTOS.forEach(internNoticeDTO -> {
            if(companyProfileFileDAO.findFileByCompanyId(internNoticeDTO.getCompanyId())==null){
            }else{
                internNoticeDTO.setFilePath(s3Service.getPreSignedUrl(companyProfileFileDAO.findFileByCompanyId(internNoticeDTO.getCompanyId()).getFilePath(), Duration.ofMinutes(10)));
            }
        });
        return internNoticeDTOS;
    }

    @Override
    public CustomUserDetails findProfile(CustomUserDetails customUserDetails) {
        if(customUserDetails.getMemberProfileUrl()!=null){
        }else {
            if(userProfileService.findProfileById(customUserDetails.getId())!=null)
            {customUserDetails.setMemberProfileUrl(userProfileService.findProfileById(customUserDetails.getId()));}
            else{
                customUserDetails.setMemberProfileUrl("/images/main-page/image3.png");
            }

        }
        return customUserDetails;
    }
}
