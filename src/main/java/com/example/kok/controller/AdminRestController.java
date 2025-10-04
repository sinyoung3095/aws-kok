package com.example.kok.controller;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.AdminExperienceDetailDTO;
import com.example.kok.dto.AdminExperienceListDTO;
import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.service.AdminService;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/**")
@RequiredArgsConstructor
public class AdminRestController {
    private final AdminService adminService;

    //    공지 목록
    @GetMapping("support/{page}")
    public AdminNoticeCriteriaDTO list(@PathVariable("page") int page){
        log.info("page = {}", page);
        return adminService.getList(page);
    }

//    체험 목록
    @GetMapping("experience/{page}")
    public AdminExperienceListDTO getExperience(@PathVariable("page") int page,
                                                @RequestParam(required = false) String keyword){
        log.info("page = {}", page);
        log.info("search = {}", keyword);
        return adminService.getExperience(page, keyword);
    }

//    체험 상세
    @GetMapping("experience/detail/{id}/{page}")
    public AdminExperienceDetailDTO getExperienceDetail(@PathVariable("id")Long id, @PathVariable("page")int page){
        log.info("page = {}", page);
        log.info("id = {}", id);
        return adminService.getExperienceDetail(page, id);
    }

}
