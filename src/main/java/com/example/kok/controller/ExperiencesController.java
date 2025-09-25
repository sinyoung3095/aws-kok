package com.example.kok.controller;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.service.ExperienceNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/experiences/**")
@RequiredArgsConstructor
public class ExperiencesController {
    private final ExperienceNoticeService experienceNoticeService;

//    목록
    @GetMapping("{page}")
    public ResponseEntity<?> expList(@PathVariable("page") int page) {
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = experienceNoticeService.selectAllExperienceNotice(page);
        if(experienceNoticeCriteriaDTO.getExperiences().size()==0||experienceNoticeCriteriaDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(experienceNoticeCriteriaDTO);
        }
        return ResponseEntity.ok(experienceNoticeCriteriaDTO);
    }
}
