package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.*;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsoleProfileServiceImpl implements ConsoleProfileService {
    private final ConsoleProfileDAO consoleProfileDAO;

    @Override
    public ConsoleCompanyProfileDTO getProfile(Long userId) {
        return consoleProfileDAO.findCompanyProfileByUserId(userId);
    }

    @Override
    @Transactional
    public void updateProfile(ConsoleCompanyProfileDTO companyProfileDTO) {
        consoleProfileDAO.updateCompanyProfile(companyProfileDTO);
        consoleProfileDAO.updateCeoName(companyProfileDTO);
        consoleProfileDAO.updateCompanySector(companyProfileDTO);
        consoleProfileDAO.updateCompanyScale(companyProfileDTO);
    }
}
