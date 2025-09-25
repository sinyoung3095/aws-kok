package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceNoticeCriteriaDTO;

public interface ConsoleExperienceService {
    public ConsoleExperienceNoticeCriteriaDTO getList(int page, String keyword);
}
