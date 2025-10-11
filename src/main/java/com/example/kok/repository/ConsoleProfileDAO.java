package com.example.kok.repository;

import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleCompanyProfileDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleAdNoticeMapper;
import com.example.kok.mapper.ConsoleProfileMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleProfileDAO {
    private final ConsoleProfileMapper consoleProfileMapper;

    // 기업 프로필
    public ConsoleCompanyProfileDTO findCompanyProfileByUserId(Long userId) {
        return consoleProfileMapper.selectCompanyProfileByUserId(userId);
    }

    // 기업 프로필 수정
    public void updateCompanyProfile(ConsoleCompanyProfileDTO companyProfileDTO) {
        consoleProfileMapper.updateCompanyProfile(companyProfileDTO);
    }

    // 대표자명 수정
    public void updateCeoName(ConsoleCompanyProfileDTO dto) {
        consoleProfileMapper.updateCeoName(dto);
    }

    // 산업 분야 수정
    public void updateCompanySector(ConsoleCompanyProfileDTO dto) {
        consoleProfileMapper.updateCompanySector(dto);
    }

    // 기업 규모 수정
    public void updateCompanyScale(ConsoleCompanyProfileDTO dto) {
        consoleProfileMapper.updateCompanyScale(dto);
    }
}
