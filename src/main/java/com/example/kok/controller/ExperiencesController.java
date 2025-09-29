package com.example.kok.controller;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.CompanyProfileFileDTO;
import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.repository.CompanyProfileFileDAO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/experiences/**")
@RequiredArgsConstructor
public class ExperiencesController {
    private final ExperienceNoticeService experienceNoticeService;
    private final CompanyProfileFileDAO companyProfileFileDAO;
    private final CompanyService companyService;

//    목록
    @GetMapping("{page}")
    public ResponseEntity<?> expList(@PathVariable("page") int page, Search search) {
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = experienceNoticeService.selectAllExperienceNotice(page, search);
        if(experienceNoticeCriteriaDTO.getExperiences().size()==0||experienceNoticeCriteriaDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(experienceNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(experienceNoticeCriteriaDTO);
    }

//    프로필 사진 url
    @GetMapping("/profile")
    public String profile(Long companyId){
        CompanyProfileFileDTO profile=companyProfileFileDAO.findFileByCompanyId(companyId);
        if(profile==null){
            return "/images/main-page/image.png";
        }
        experienceNoticeService.setPreSignedUrl(profile);
        return profile.getFilePath();
    }

//    상세 불러오기
    @GetMapping("/detail")
    public Map<String,Object> detail(Long companyId, Long experienceId) {
        Map<String,Object> result = new HashMap<>();
        result.put("notice", experienceNoticeService.findNoticeById(experienceId));
        result.put("company", companyService.findCompanyById(companyId));
        return result;
    }
}
