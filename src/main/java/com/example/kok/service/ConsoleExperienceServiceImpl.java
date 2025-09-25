package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceNoticeCriteriaDTO;
import com.example.kok.repository.ConsoleExperienceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsoleExperienceServiceImpl implements ConsoleExperienceService {
    private final ConsoleExperienceDAO consoleExperienceDAO;

    @Override
    public ConsoleExperienceNoticeCriteriaDTO getList(int page, String keyword) {

        return null;
    }
}
