package com.example.kok.service;

import com.example.kok.dto.ConsoleCompanyProfileDTO;

public interface ConsoleProfileService {
    ConsoleCompanyProfileDTO getProfile(Long userId);

    void updateProfile(ConsoleCompanyProfileDTO companyProfileDTO);

}
