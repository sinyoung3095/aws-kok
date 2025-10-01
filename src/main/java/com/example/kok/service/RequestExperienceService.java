package com.example.kok.service;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestExperienceFileDTO;

import java.util.List;

public interface RequestExperienceService {
//    지원서 내기
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO, List<Long> fileIds);
}
